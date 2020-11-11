package com.xw.util.learn;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 {
    int i = 0;
    int i2 = 0;
    ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        /*for (int i = 0; i < 10; i++) {
            t1();
        }*/
        int k = 0;
        // 1 + 1 +3 +3 +5
        int str = ++k + k++ + ++k + k++ + ++k;
        System.out.println(str + " - " + k);
        // k=5
    }

    private static void t1() {
        Test1 test1 = new Test1();

        int size = 100;
        CountDownLatch countDownLatch = new CountDownLatch(size);

        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
                test1.get1();
            }).start();
        }

        try {
            countDownLatch.await();
            System.out.println(test1.i + "  -- " + test1.i2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void get1() {
//        reentrantLock.lock();

        i++;
//        reentrantLock.unlock();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        get2();

        //System.out.println(Thread.currentThread().getName() + " A " + i);
    }

    public void get2() {
        i2++;
        // System.out.println(Thread.currentThread().getName() + " B " + i2);
    }
}
