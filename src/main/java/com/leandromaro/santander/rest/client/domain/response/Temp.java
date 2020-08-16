
package com.leandromaro.santander.rest.client.domain.response;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Temp {

    private double day;
    private double eve;
    private double max;
    private double min;
    private double morn;
    private double night;

}
