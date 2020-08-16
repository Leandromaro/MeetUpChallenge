
package com.leandromaro.santander.rest.client.domain.response;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class FeelsLike {

    private double day;
    private double eve;
    private double morn;
    private double night;

}
