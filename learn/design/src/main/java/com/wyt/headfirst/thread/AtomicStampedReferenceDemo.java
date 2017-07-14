package com.wyt.headfirst.thread;

import scala.Int;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/20.
 */
public class AtomicStampedReferenceDemo {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19, 0);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            final int temestamp = money.getStamp();
            new Thread(() -> {
                while (true) {
                    while (true) {
                        Integer m = money.getReference();
                        if (m < 20) {
                            if (money.compareAndSet(m, m + 20, temestamp, temestamp + 1)) {
                                System.out.println("余额小于20元,充值成功!,余额:" + money.getReference() + "元");
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
                    int stamp = money.getStamp();
                    Integer m = money.getReference();
                    if (m > 10) {
                        System.out.println("大于10元");
                        if (money.compareAndSet(m, m - 10, stamp, stamp + 1)) {
                            System.out.println("成功消费10元,余额:" + money.getReference() + "元");
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
