package com.wyt.headfirst.observer.observer;

/**
 * 订阅者
 *
 * @author Darcy
 *         Created by Administrator on 2016/8/24.
 */
public interface IObserver {
    /**
     * 当气象信息改变时,更新所有订阅者
     *
     * @param temp     温度
     * @param humidity 湿度
     * @param pressure 压力
     */
    void update(float temp, float humidity, float pressure);
}
