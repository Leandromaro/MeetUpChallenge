package com.leandromaro.santander.rest.controller;

import com.leandromaro.santander.rest.exceptions.MeetUpUserEventNotFoundException;
import com.leandromaro.santander.rest.exceptions.MeetUpNotFoundException;
import com.leandromaro.santander.rest.exceptions.UserNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
@Order(Ordered.LOWEST_PRECEDENCE)
public class MeetUpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, MeetUpNotFoundException.class, MeetUpUserEventNotFoundException.class})
    public ResponseEntity<String> handleCustomException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}