
package com.leandromaro.santander.rest.client.domain.response.darkSky;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class DarkSkyResponse {

    private Currently currently;
    private Daily daily;
    private Flags flags;
    private Hourly hourly;
    private double latitude;
    private double longitude;
    private long offset;
    private String timezone;

}
