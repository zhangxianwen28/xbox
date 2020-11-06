package com.xw.util;

import com.google.common.base.Strings;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RedisUtils {
    /**
     * 删除对应的value
     *
     * @param key
     */
    public static void remove(RedisTemplate redisTemplate, final String key) {
        if (exists(redisTemplate, key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public static boolean exists(RedisTemplate redisTemplate, final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public static String valueOf(RedisTemplate redisTemplate, String key) {
        ValueOperations<Serializable, String> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 写入缓存，永不过期
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean save(RedisTemplate redisTemplate, final String key, String value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param redisTemplate
     * @param key           redis中用于保存更新时间的key,每个库对应唯一的key
     * @param someDate      新的更新时间,format: yyyy-MM-dd
     */
    public static void CASLastUpdateDate(RedisTemplate redisTemplate, String key, String someDate) {
        if (Strings.isNullOrEmpty(someDate) || !someDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("参数错误");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String lastUpdateDate = valueOf(redisTemplate, key);
        //key存在且值有效
        if (!Strings.isNullOrEmpty(lastUpdateDate)) {
            LocalDate dateInRedis = LocalDate.parse(lastUpdateDate, formatter);
            LocalDate newDate = LocalDate.parse(someDate, DateTimeFormatter.ISO_DATE);
            if (newDate.isAfter(dateInRedis)) {
                save(redisTemplate, key, newDate.format(formatter));
            }
        } else {
            save(redisTemplate, key, LocalDate.parse(someDate, DateTimeFormatter.ISO_DATE).format(formatter));
        }
    }
}
