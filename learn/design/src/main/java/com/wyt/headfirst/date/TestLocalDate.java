package com.wyt.headfirst.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/20.
 */
public class TestLocalDate {
    public static void main(String[] args) {
        //2017-03-20
        LocalDate date = LocalDate.of(2017, 3, 20);
        int year = date.getYear();//2017  返回年份
        Month month = date.getMonth();//MARCH 返回月份 3月
        int dayOfMonth = date.getDayOfMonth();//20 返回月中的第几天
        DayOfWeek dayOfWeek = date.getDayOfWeek();//MONDAY 返回星期几
        int len = date.lengthOfMonth();//31 返回这个月有多少天
        boolean leapYear = date.isLeapYear(); //false 是否是闰年

        //获取系统时间
        LocalDate now = LocalDate.now(); //相当于 new Date()

    }
}
