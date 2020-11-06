package com.xw.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 批处理
 *
 * @author xw.zhang
 */
@Slf4j
public class BatchTask4 {
    // 生产队列
    private final BlockingQueue<List<String>> supplierQueue = new LinkedBlockingQueue<>(5000);
    // 任务总数量
    private final Integer totalTask;
    // 已处理任务数量
    private final AtomicInteger handleTaskCount = new AtomicInteger(0);
    // 任务最大序号
    private final Integer maxSN;
    // 生产计数器
    private final AtomicLong producerCounter = new AtomicLong(0);
    // 消费计数器
    public final AtomicLong consumerCounter = new AtomicLong(0);


    private BatchTask4(int startSN, int endSN, int snSize) {
        this.totalTask = ((endSN - startSN) * snSize);
        this.maxSN = endSN;
        this.producerCounter.set(startSN);
        this.consumerCounter.set(startSN);
        log.info("处理任务数量 {}  当前序号 {}  最大序号 {} ", totalTask, producerCounter, maxSN);
    }

    public static BatchTask4 custom(int startSN, int endSN, int size) {
        BatchTask4 batchTask4 = new BatchTask4(startSN, endSN, size);
        batchTask4.analyze();
        return batchTask4;
    }

    /**
     * 生产者
     */
    public BatchTask4 producer(Function<Long, List<String>> consumer, long threadCount) {
        for (int j = 0; j < threadCount; j++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        long sn = producerCounter.incrementAndGet();
                        if (sn > maxSN) {
                            break;
                        }
                        Instant start = Instant.now();
                        supplierQueue.put(consumer.apply(sn));
                        if (log.isDebugEnabled()) {
                            log.debug("生产耗时: {} {}", Duration.between(start, Instant.now()).toMillis(), sn);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.setName("producer " + j);
            thread.start();
        }
        return this;
    }

    /**
     * 消费者
     */
    public void consumer(Consumer<List<String>> consumer, int threadCount, RedisTemplate redisTemplate) {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        long sn = consumerCounter.incrementAndGet();
                        if (sn > maxSN) {
                            log.info("初始化断点续传值为0 ,消费结束");
                            RedisUtils.save(redisTemplate, "batchPatent", "0");
                            break;
                        }
                        List<String> take = supplierQueue.take();
                        if (take.isEmpty()) {
                            log.warn("获取任务 take.isEmpty()");
                            continue;
                        }
                        Instant start = Instant.now();
                        int size = take.size();
                        consumer.accept(take);
                        log.info("耗时: {}     更新节点: {}", (Duration.between(start, Instant.now()).toMillis()),  sn);
                        handleTaskCount.addAndGet(size);
                        RedisUtils.save(redisTemplate, "batchPatent", String.valueOf(sn));
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
                countDownLatch.countDown();
            });
            thread.setName("consumer " + i);
            thread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            supplierQueue.clear();
            handleTaskCount.set(0);
        }

    }


    private void analyze() {
        Instant start = Instant.now();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(45 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    if (handleTaskCount.get() > 0) {
                        int hd = handleTaskCount.get();
                        long last = totalTask - hd;
                        long millis = Duration.between(start, Instant.now()).toMillis();

                        BigDecimal processed2 = new BigDecimal(String.valueOf(hd));
                        BigDecimal millis2 = new BigDecimal(String.valueOf(millis));
                        long percentage = processed2.divide(new BigDecimal(String.valueOf(totalTask == 0 ? 1 : totalTask)), 6, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal("100")).intValue();   //百分比

                        log.info("监控 总数量: {} | 当前: {} | 剩余: {} | 进度: {}% 已用时间: {}", totalTask, hd, last, percentage, fmt(millis2)
                        );
                        Thread.sleep(1000 * 60);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private String fmt(BigDecimal val) {
        String msg = "";
        BigDecimal second = new BigDecimal(String.valueOf(1000));
        BigDecimal minute = new BigDecimal(String.valueOf((60) * 1000));
        BigDecimal hours = new BigDecimal(String.valueOf((60 * 60) * 1000));
        BigDecimal day = new BigDecimal(String.valueOf((60 * 60 * 24) * 1000));
        if (val.compareTo(day) > 0) {
            BigDecimal divide = val.divide(day, 6, BigDecimal.ROUND_HALF_DOWN);
            msg = divide.intValue() + "天 ";
            val = val.subtract(day.multiply(new BigDecimal(divide.intValue())));
        }
        if (val.compareTo(hours) > 0) {
            BigDecimal divide = val.divide(hours, 6, BigDecimal.ROUND_HALF_DOWN);
            msg = msg + divide.intValue() + "小时 ";
            val = val.subtract(hours.multiply(new BigDecimal(divide.intValue())));
        }
        if (val.compareTo(minute) > 0) {
            BigDecimal divide = val.divide(minute, 6, BigDecimal.ROUND_HALF_DOWN);
            msg = msg + divide.intValue() + "分钟 ";
            val = val.subtract(day.multiply(new BigDecimal(divide.intValue())));
        }
        if (val.compareTo(second) > 0) {
            BigDecimal divide = val.divide(second, 6, BigDecimal.ROUND_HALF_DOWN);
            msg = msg + divide.intValue() + "秒 ";
        }
        return msg;
    }


}
