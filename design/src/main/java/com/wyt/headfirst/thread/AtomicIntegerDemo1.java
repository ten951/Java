package com.wyt.headfirst.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/20.
 */
public class AtomicIntegerDemo1 {
    static AtomicReference<Integer> money = new AtomicReference<>();

    public static void main(String[] args) {
        money.set(19);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    while (true) {
                        Integer m = money.get();
                        if (m < 20) {
                            if (money.compareAndSet(m, m + 20)) {
                                System.out.println("余额小于20元,充值成功!,余额:" + money.get() + "元");
                                break;
                            }
                        } else {
                            //System.out.println("余额大于20元,无须充值!");
                            break;
                        }
                    }
                }
            }).start();
        }
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                while (true) {
                    Integer m = money.get();
                    if (m > 10) {
                        System.out.println("大于10元");
                        if (money.compareAndSet(m, m - 10)) {
                            System.out.println("成功消费10元,余额:" + money.get() + "元");
                            break;
                        }
                    } else {
                        System.out.println("没有足够的金额");
                        break;
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
