
package com.leandromaro.santander.rest.client.domain.response;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class List {

    private long clouds;
    private long deg;
    private long dt;
    private FeelsLike feelsLike;
    private long humidity;
    private long pop;
    private long pressure;
    private double speed;
    private long sunrise;
    private long sunset;
    private Temp temp;
    private java.util.List<Weather> weather;

}
