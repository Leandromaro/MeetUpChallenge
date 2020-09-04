package com.leandromaro.challenge.rest.controller;

import com.leandromaro.challenge.rest.exceptions.MeetUpUserEventNotFoundException;
import com.leandromaro.challenge.rest.exceptions.MeetUpNotFoundException;
import com.leandromaro.challenge.rest.exceptions.UserNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
@Order(Ordered.LOWEST_PRECEDENCE)
public class MeetUpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, MeetUpNotFoundException.class, MeetUpUserEventNotFoundException.class, HttpClientErrorException.class})
    public ResponseEntity<String> handleCustomException() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleCustomIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}