package com.leandromaro.challenge.rest.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMeetUpRequest {

    private String firstName;

    private String lastName;

}
