package com.wyt.headfirst.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/20.
 */
public class TestInstant {
    public static void main(String[] args) {

        Period between = Period.between(LocalDate.of(2017, 3, 19), LocalDate.of(2017, 3, 20));
        System.out.println("between = " + between.getMonths());
        Period tenDays = Period.ofDays(10);
        System.out.println("tenDays = " + tenDays);
    }
}
