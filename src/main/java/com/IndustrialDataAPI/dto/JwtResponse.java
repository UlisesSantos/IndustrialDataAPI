package com.IndustrialDataAPI.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public class JwtResponse {

    @NotBlank
    private String accessToken;

    @NotBlank
    private String refreshToken;

    public JwtResponse() {
    }

    public JwtResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
