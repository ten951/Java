package com.wyt.headfirst.date;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/20.
 */
public class TestTemporalAdjuster {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2017, 3, 20);
        LocalDate date1 = date.with(nextOrSame(DayOfWeek.SUNDAY));//下一个周日 2017-03-26
        LocalDate date3 = date1.with(lastDayOfMonth());//当月最后一天 2017-03-31
        System.out.println(date3);
        //dayOfWeekInMonth  创建一个新的日期,它的值为同一个月中每一周的第几天,
        //firstDayOfMonth  创建一个新的日期,它的值为当月的第一天
        //firstDayOfNextMonth  创建一个新的日期,它的值为下个月的第一天
        //firstDayOfNextYear  创建一个新的日期,它的值为明年的第一天
        //firstDayOfYear  创建一个新的日期,它的值为当年的第一天
        //firstInMonth 创建一个新的日期 它的值同一个月中,第一个符合星期几要求的值
        //lastDayOfMonth  创建一个新的日期,它的值为当月的最后一天
        //lastDayOfNextMonth  创建一个新的日期,它的值为下个月的最后一天
        //lastDayOfNextYear  创建一个新的日期,它的值为明年的最后一天
        //lastDayOfYear  创建一个新的日期,它的值为当年的最后一天
        //lastInMonth 创建一个新的日期 它的值同一个月中,最后一个符合星期几要求的值
        //next/previous 创建一个新的日期 并将其值设定为日期调整后或者调整前,第一个符合指定星期几要求的日期
        //nextOrSame/previousOrSame 创建一个新的日期 并将其值设定为日期调整后或者调整前,第一个符合指定星期几要求的日期 如果该日期符合要求,直接返回改对象

    }

}
