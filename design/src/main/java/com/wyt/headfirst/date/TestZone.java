package com.wyt.headfirst.date;

import java.time.*;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.TimeZone;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/20.
 */
public class TestZone {
    public static void main(String[] args) {

        //地区ID都为{区域}/{城市}的格式,这些地区集合的设定都由英特网编号分配机构(IANA)的市区数据库提供
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        //可以通过Java8的新方法 将老的时区装换为ZoneId
        ZoneId zoneId = TimeZone.getDefault().toZoneId();

        LocalDate date = LocalDate.of(2017, 3, 20);
        ZonedDateTime zonedDateTime = date.atStartOfDay(romeZone);
        LocalDateTime dateTime = LocalDateTime.of(2017, 3, 20, 14, 41, 40);
        ZonedDateTime zonedDateTime1 = dateTime.atZone(romeZone);
        Instant now = Instant.now();
        ZonedDateTime zonedDateTime2 = now.atZone(romeZone);

        //使用别的日历系统
        //java8 另外提供了4中其他的日历系统 ThaiBuddhistDate MinguoDate JapaneseDate HijrahDate
        ThaiBuddhistDate now1 = ThaiBuddhistDate.now();//ThaiBuddhist BE 2560-03-20
        MinguoDate now2 = MinguoDate.now();//Minguo ROC 106-03-20
        JapaneseDate now3 = JapaneseDate.now();//Japanese Heisei 29-03-20
        HijrahDate now4 = HijrahDate.now();//Hijrah-umalqura AH 1438-06-21
        JapaneseDate from = JapaneseDate.from(date); //Japanese Heisei 29-03-20


    }
}
