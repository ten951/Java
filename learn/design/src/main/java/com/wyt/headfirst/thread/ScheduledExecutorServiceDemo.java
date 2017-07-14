package com.wyt.headfirst.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/13.
 */
public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
     /*   ses.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(8000);
                System.out.println(System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 2, TimeUnit.SECONDS);*/
        ses.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(8000);
                System.out.println(System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}
