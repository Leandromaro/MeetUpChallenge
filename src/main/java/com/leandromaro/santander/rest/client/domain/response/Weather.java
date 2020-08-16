
package com.leandromaro.santander.rest.client.domain.response;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Weather {

    private String description;
    private String icon;
    private long id;
    private String main;

}
