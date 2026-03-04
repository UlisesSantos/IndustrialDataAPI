package com.IndustrialDataAPI.exception;

public class NullOrEmptyParametersException extends RuntimeException {
    public NullOrEmptyParametersException() {
        super("Value is null or empty");
    }
}
