package com.wyt.headfirst.date;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/20.
 */
public class ControlDate {
    public static void main(String[] args) {
        //以直观的方式操作LocalDate属性
        LocalDate date = LocalDate.of(2017, 3, 20);//2017-3-20
        LocalDate localDate = date.withYear(2011);//2011-3-20
        LocalDate localDate1 = localDate.withDayOfMonth(25);//2011-3-25
        LocalDate with = localDate1.with(ChronoField.MONTH_OF_YEAR, 9);//2011-9-25

        //以相对的方式操作LocalDate属性

        LocalDate localDate2 = date.plusWeeks(1);//一周后 2017-03-27
        LocalDate localDate3 = localDate2.minusYears(3);//3年前 2014-03-27
        LocalDate plus = localDate3.plus(6, ChronoUnit.MONTHS);//6个月后 2014-09-27
        //Temporal是接口  所有的日期时间类 实现了该接口
        //from 依据传入的Temporal对象创建对象实例
        //now 依据系统时钟创建Temporal对象
        //of 有Temporal对象的某个部分创建该对象的实例
        //parse 由字符串创建Temporal对象的实例
        //atOffset 将Temporal对象和某个时区偏移相结合
        //atZone 将Temporal对象和某个时区相结合
        //format 使用某个指定的格式器将Temporal对象转换为字符串(Instant类不提供该方法)
        //get 读取Temporal对象的某一部分
        //minus 创建Temporal对象的一个副本,通过将当前Temporal对象的值减去一定的时长创建该副本
        //plus 创建Temporal对象的一个副本,通过将当前Temporal对象的值加上一定的时长创建该副本
        //with 以该Temporal对象为模板,对某些状态进行修改创建该对象的副本
    }
}
