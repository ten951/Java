package com.wyt.headfirst.date;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/20.
 */
public class TestLocalTime {
    public static void main(String[] args) {
        //14:33:45
        LocalTime time = LocalTime.of(14, 33, 45);
        int hour = time.getHour();//时
        int minute = time.getMinute();//分
        int second = time.getSecond();//秒

        //还可以通过解析代表他们的字符串创建.
        LocalDate date = LocalDate.parse("2017-03-20");
        LocalDate dateTime = LocalDate.parse("14:33:20");
    }
}
