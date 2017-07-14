package com.wyt.headfirst.observer.subject;

import com.wyt.headfirst.observer.observer.CurrentConditionsDisplay;

/**
 * @author Darcy
 *         Created by Administrator on 2016/8/24.
 */
public class WeatherTest {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(80,65,30.4f);
        weatherData.setMeasurements(82,70,29.2f);
        weatherData.setMeasurements(78,90,29.2f);
    }
}
