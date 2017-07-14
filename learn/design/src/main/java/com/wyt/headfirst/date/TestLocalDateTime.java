package com.wyt.headfirst.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/20.
 */
public class TestLocalDateTime {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2017, 3, 20);
        LocalTime time = LocalTime.of(14, 33, 45);
        LocalDateTime dateTime = LocalDateTime.of(2017, 3, 20, 14, 41, 40);
        LocalDateTime dateTime1 = LocalDateTime.of(date, time);
        LocalDateTime dateTime2 = date.atTime(14, 33, 45);
        LocalDateTime dateTime3 = date.atTime(time);
        LocalDateTime dateTime4 = time.atDate(date);
        //从LocalDateTime装换为LocalDate和LocalTime
        LocalDate localDate = dateTime.toLocalDate();
        LocalTime localTime = dateTime.toLocalTime();

    }
}
