package com.wyt.headfirst.observer.subject;

import com.wyt.headfirst.observer.observer.IObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Darcy
 *         Created by Administrator on 2016/8/24.
 */
public class WeatherData implements ISubject {

    private List<IObserver> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    /**
     * 通知所有订阅者
     */
    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(temperature, humidity, pressure);
        }

    }

    /**
     * 注册订阅者
     *
     * @param o 订阅者对象
     */
    @Override
    public void registerObserver(IObserver o) {
        observers.add(o);
    }

    /**
     * 取消订阅
     *
     * @param o 订阅者对象
     */
    @Override
    public void removerObserver(IObserver o) {
        int i = observers.indexOf(o);
        if (i > 0) {
            observers.remove(o);
        }
    }

    /**
     * 当气象站得到更新观测值时,我们通知订阅者
     */
    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float pressure, float temperature, float humidity) {
        this.pressure = pressure;
        this.temperature = temperature;
        this.humidity = humidity;
        measurementsChanged();
    }
}
