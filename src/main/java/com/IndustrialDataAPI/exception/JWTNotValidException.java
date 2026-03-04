package com.IndustrialDataAPI.exception;

public class JWTNotValidException extends RuntimeException {
    public JWTNotValidException() {
        super("The JWT Token is not valid");
    }
}
