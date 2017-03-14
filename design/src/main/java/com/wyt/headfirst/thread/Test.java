package com.wyt.headfirst.thread;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/6.
 */
public class Test {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("Hello,I am 他");
            }
        };
        t1.start();
        //Java8 版本
        Thread t2 = new Thread(() -> System.out.println("Hello,I am 他"));
        t2.start();
    }
}
