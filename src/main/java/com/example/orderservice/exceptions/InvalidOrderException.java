package com.example.orderservice.exceptions;

public class InvalidOrderException extends Exception{
    public InvalidOrderException(String message) {
        super(message);
    }
}
