package com.xw.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtil {

    public static void main(String[] args) {
        LocalDate localDate =LocalDate.now();
        System.out.println("获取当前时间："+localDate);

        LocalDate localDate1 = localDate.plusYears(2);
        System.out.println("在原有基础上加2年"+localDate1);

        LocalDate localDate2 = localDate.minusYears(2);
        System.out.println("在原有基础上减2年"+localDate2);

        long timestamp = LocalDate.of(1999,9,9).atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
        System.out.println("获取时间戳："+timestamp);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("获取当前时间："+localDateTime);



        Instant instant = Instant.now();//默认获取的是UTC时区时间，世界协调时间，
        System.out.println(instant);
    }
}
