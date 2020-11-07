package com.xw.education.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {


    public static void main(String[] args) throws InterruptedException {
        List<LocalDateTime> dateTimes = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (int i = 1; i < 11; i++) {
            LocalDateTime of = LocalDateTime.of(2001, 12, i, 12, 12, 12);
            dateTimes.add(of);
            dateTimes.add(null);
            System.out.println(i + " : " + dateTimeFormatter.format(of));
        }
        LocalDateTime localDateTime = dateTimes.stream().min(LocalDateTime::compareTo).get();
        System.out.println("min : " + dateTimeFormatter.format(localDateTime));

        LocalDateTime localDateTime2 = dateTimes.stream().max(LocalDateTime::compareTo).get();
        System.out.println("max : " + dateTimeFormatter.format(localDateTime2));
    }


}
