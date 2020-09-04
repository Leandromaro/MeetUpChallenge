
package com.leandromaro.challenge.rest.client.domain.response.darkSky;

import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Flags {

    private double nearestStation;
    private List<String> sources;
    private String units;

}
