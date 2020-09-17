package com.example.demogradle.test;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * @description: LocalDateTime java8特性
 * @author zhangChuan
 * @date 2020/9/15 4:45 下午
 */
@Slf4j
public class LocalDateTimeDemo {

    public static void main(String[] args) {

        //获取当前日期时间
        LocalDateTime localDateTime = LocalDateTime.now();
        int dayOfYear = localDateTime.getDayOfYear();

        //年
        int year = localDateTime.getYear();
        //月
        int month = localDateTime.getMonth().getValue();
        //日
        int dayOfMonth = localDateTime.getDayOfMonth();
        //时
        int hour = localDateTime.getHour();
        //分
        int minute = localDateTime.getMinute();
        //秒
        int second = localDateTime.getSecond();

        log.info("dayOfYear:{}",dayOfYear);
        log.info("date:{}-{}-{}",year,month,dayOfMonth);
        log.info("hour:{};minute:{};second:{}",hour,minute,second);


        LocalDateTime date2 = localDateTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);

    }

}
