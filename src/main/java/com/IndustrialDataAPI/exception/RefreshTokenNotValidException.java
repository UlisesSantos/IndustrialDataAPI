package com.IndustrialDataAPI.exception;

public class RefreshTokenNotValidException extends RuntimeException {
    public RefreshTokenNotValidException() {
        super("Refresh token not valid");
    }
}
