package com.wyt.headfirst.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/23.
 */
public class ThreadPoolUtils {
    private ThreadPoolUtils() {
        System.out.println("ssssss");
    }
    private static class ThreadPoolHolder {
        private static ExecutorService executor = Executors.newFixedThreadPool(30);
    }

    public static ExecutorService getExecutor() {
        return ThreadPoolHolder.executor;
    }

    public static void main(String[] args) {
        Executor executor = ThreadPoolUtils.getExecutor();


        System.out.println("executor = " + executor);
    }
}
