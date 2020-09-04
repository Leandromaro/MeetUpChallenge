
package com.leandromaro.challenge.rest.client.domain.response.darkSky;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Datum {

    private double apparentTemperature;
    private double apparentTemperatureHigh;
    private long apparentTemperatureHighTime;
    private double apparentTemperatureLow;
    private long apparentTemperatureLowTime;
    private double apparentTemperatureMax;
    private long apparentTemperatureMaxTime;
    private double apparentTemperatureMin;
    private long apparentTemperatureMinTime;
    private double cloudCover;
    private double dewPoint;
    private double humidity;
    private String icon;
    private double moonPhase;
    private double ozone;
    private long precipIntensity;
    private double precipIntensityMax;
    private long precipIntensityMaxTime;
    private long precipProbability;
    private String precipType;
    private double pressure;
    private String summary;
    private long sunriseTime;
    private long sunsetTime;
    private double temperature;
    private double temperatureHigh;
    private long temperatureHighTime;
    private double temperatureLow;
    private long temperatureLowTime;
    private double temperatureMax;
    private long temperatureMaxTime;
    private double temperatureMin;
    private long temperatureMinTime;
    private long time;
    private long uvIndex;
    private long uvIndexTime;
    private long visibility;
    private long windBearing;
    private double windGust;
    private long windGustTime;
    private double windSpeed;

}
