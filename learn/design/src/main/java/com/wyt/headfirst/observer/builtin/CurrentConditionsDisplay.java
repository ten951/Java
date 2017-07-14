package com.wyt.headfirst.observer.builtin;

import com.wyt.headfirst.observer.observer.IDisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Darcy
 *         Created by Administrator on 2016/8/24.
 */
public class CurrentConditionsDisplay implements Observer, IDisplayElement {
    Observable obervable;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Observable obervable) {
        this.obervable = obervable;
        obervable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param obs the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }

}
