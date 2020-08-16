package com.leandromaro.santander.rest.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MeetUpResponse {

    Long id;

    private LocalDate date;

    private String address;

    private String name;
}
