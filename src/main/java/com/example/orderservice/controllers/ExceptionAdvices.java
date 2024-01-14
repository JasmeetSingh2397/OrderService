package com.example.orderservice.controllers;

import com.example.orderservice.exceptions.InvalidOrderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler({InvalidOrderException.class})
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(InvalidOrderException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
