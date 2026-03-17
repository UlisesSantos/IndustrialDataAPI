package com.IndustrialDataAPI.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public class RefreshTokenRequest {

    @NotBlank
    private String refreshToken;

    public RefreshTokenRequest(){}

    public RefreshTokenRequest(String refreshToken){
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken(){
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

}
