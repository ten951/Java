package com.wyt.headfirst.observer.observer;

import com.wyt.headfirst.observer.subject.ISubject;

import java.util.Observer;

/**
 * @author Darcy
 *         Created by Administrator on 2016/8/24.
 */
public class CurrentConditionsDisplay implements IObserver, IDisplayElement {
    private float temperature;
    private float humidity;
    private ISubject weatherData;

    public CurrentConditionsDisplay(ISubject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }

    /**
     * 当气象信息改变时,更新所有订阅者
     *
     * @param temp     温度
     * @param humidity 湿度
     * @param pressure 压力
     */
    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }
}
