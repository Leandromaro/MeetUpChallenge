
package com.leandromaro.santander.rest.client.domain.response;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class WeatherResponse {

    private City city;
    private long cnt;
    private String cod;
    private java.util.List<List> list;
    private double message;

}
