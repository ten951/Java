package com.wyt.headfirst.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.*;

/**
 * 定制TemporalAdjuster 能计算明天的日期,同时过滤掉周六和周日
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/20.
 */
public class NextWorkingDay implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        //读取当天日期
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;//正常情况下增加一天
        if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
        if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2017, 3, 20);
        LocalDate with = date.with(new NextWorkingDay());
        //或者用lambda表达式 但是这种不利于复用
        LocalDate with1 = date.with(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;//正常情况下增加一天
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        //便于复用
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(temporal -> {
            //读取当天日期
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;//正常情况下增加一天
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });

        date.with(nextWorkingDay);
    }
}
