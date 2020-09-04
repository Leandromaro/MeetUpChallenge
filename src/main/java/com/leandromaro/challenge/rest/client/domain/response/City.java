
package com.leandromaro.challenge.rest.client.domain.response;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class City {

    private Coord coord;
    private String country;
    private long id;
    private String name;
    private long population;
    private long timezone;

}
