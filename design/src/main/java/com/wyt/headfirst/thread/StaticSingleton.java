package com.wyt.headfirst.thread;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/21.
 */
public class StaticSingleton {
    private StaticSingleton() {

    }
    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
