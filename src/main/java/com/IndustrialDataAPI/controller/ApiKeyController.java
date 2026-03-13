package com.IndustrialDataAPI.controller;

import com.IndustrialDataAPI.dto.ApiKeysRequest;
import com.IndustrialDataAPI.service.ApiKeyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("industrial")
public class ApiKeyController {

    private static final Logger LOGGER = LogManager.getLogger(ApiKeyController.class);

    @Autowired
    private ApiKeyService apiKeyService;

    @PostMapping("/api-key")
    public ResponseEntity<String> createApiKey(@RequestBody ApiKeysRequest request, Authentication authentication){
        LOGGER.info("Creating a new Api Key for Machine {} by {}",request.getMachine_id(), authentication.getName());
        String apiKey = apiKeyService.generateApiKey();
        apiKeyService.saveApiKey(request.getService_name(), request.getMachine_id(), apiKey);
        LOGGER.info("Api Key created by {}", authentication.getName());
        return ResponseEntity.ok(apiKey);
    }
}
