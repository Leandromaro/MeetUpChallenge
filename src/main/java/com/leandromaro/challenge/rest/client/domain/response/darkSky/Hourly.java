
package com.leandromaro.challenge.rest.client.domain.response.darkSky;

import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Hourly {

    private List<Datum> data;
    private String icon;
    private String summary;

}
