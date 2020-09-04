package com.leandromaro.challenge.rest.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MeetUpResponse {

    Long id;

    private String address;

    private String name;

    private String city;
}
