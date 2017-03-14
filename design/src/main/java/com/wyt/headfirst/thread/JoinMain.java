package com.wyt.headfirst.thread;

import org.apache.commons.math3.analysis.function.Add;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/6.
 */
public class JoinMain {
    public volatile static int i = 0;

    public static class AddThread extends Thread {
        public void run() {
            for (i = 0; i < 10000000; i++) ;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread at = new AddThread();
        at.start();
        at.join();
        System.out.println(i);
    }
}
