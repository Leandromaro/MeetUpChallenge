
package com.leandromaro.challenge.rest.client.domain.response.darkSky;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Currently {

    private double apparentTemperature;
    private long cloudCover;
    private double dewPoint;
    private double humidity;
    private String icon;
    private double ozone;
    private long precipIntensity;
    private long precipProbability;
    private double pressure;
    private String summary;
    private double temperature;
    private long time;
    private long uvIndex;
    private long visibility;
    private long windBearing;
    private double windGust;
    private double windSpeed;

}
