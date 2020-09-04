package com.leandromaro.challenge.rest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetUpNotFoundException extends RuntimeException{
    private String message;
}
