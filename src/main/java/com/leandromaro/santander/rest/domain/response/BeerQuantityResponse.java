package com.leandromaro.santander.rest.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeerQuantityResponse {

    Integer beerQuantity;

    Integer beerBoxesQuantity;

    String celsiusWeather;
}
