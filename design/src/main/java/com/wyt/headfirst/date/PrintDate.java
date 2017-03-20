package com.wyt.headfirst.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/20.
 */
public class PrintDate {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2017, 3, 20);
        String format = date.format(DateTimeFormatter.BASIC_ISO_DATE);//20170320
        String format2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);//2017-03-20

        LocalDate.parse("20170320", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate.parse("2017-03-20", DateTimeFormatter.ISO_LOCAL_DATE);

        //按照某种格式创建DateTimeFormatter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String format1 = date.format(dateTimeFormatter);
        LocalDate parse = LocalDate.parse(format1, dateTimeFormatter);

        //如果需要更加细粒度的控制,DateTimeFormatterBuilder类提供了更复杂的格式器,可以使用 一步一步的构造自己的格式器
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()//对大小写不敏感
                .toFormatter(Locale.CHINA);
        LocalDate date3 = LocalDate.of(2017, 3, 20);
        String format3 = date3.format(formatter); //20. 三月 2017

    }
}
