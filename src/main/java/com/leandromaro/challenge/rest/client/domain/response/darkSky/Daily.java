
package com.leandromaro.challenge.rest.client.domain.response.darkSky;

import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Daily {

    private List<Datum> data;

}
