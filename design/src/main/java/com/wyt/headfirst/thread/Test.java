package com.wyt.headfirst.thread;

import com.wyt.headfirst.lambda.Apple;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/6.
 */
public class Test{
    public static void main(String[] args) {
       /* Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("Hello,I am 他");
            }
        };
        t1.start();
        //Java8 版本
        Thread t2 = new Thread(() -> System.out.println("Hello,I am 他"));
        t2.start();*/

        Map<String,Apple> locMap = new ConcurrentHashMap<>();
        Apple apple = new Apple();
        apple.setWeight(100);
        apple.setColor("red");
        locMap.put("1",apple);

        Apple apple1 = new Apple();
        apple1.setWeight(100);
        apple1.setColor("red1");
        locMap.put("1",apple1);
        System.out.println("locMap = " + locMap);
    }
}
