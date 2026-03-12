package com.IndustrialDataAPI.service;

import com.IndustrialDataAPI.model.ApiKeys;
import com.IndustrialDataAPI.repository.ApiKeyRepository;
import com.IndustrialDataAPI.security.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
public class ApiKeyService {

    @Autowired
    private MachineService machineService;

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public String generateApiKey(){
        byte[] apiKeyBytes = new byte[32];
        SECURE_RANDOM.nextBytes(apiKeyBytes);
        String prefix = generatePrefix();
        return "ap_" + prefix + Base64.getUrlEncoder().withoutPadding().encodeToString(apiKeyBytes);
    }

    private String generatePrefix(){
        byte[] prefixBytes = new byte[4];
        SECURE_RANDOM.nextBytes(prefixBytes);
        String prefix = Base64.getUrlEncoder().withoutPadding().encodeToString(prefixBytes);
        Optional<ApiKeys> apiKeysOptional = apiKeyRepository.findByApiKeyStartingWith(prefix);
        return (apiKeysOptional.isPresent()) ? generatePrefix() : prefix;
    }

    public void saveApiKey(String serviceName, Long machineId, String apiKey){
        ApiKeys apiKeys = new ApiKeys(apiKey.substring(0,9) + myPasswordEncoder.encode(apiKey.substring(9)),
                serviceName,
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(12),
                true,
                machineService.getMachineById(machineId));

        apiKeyRepository.save(apiKeys);
    }

    public boolean isApiKeyExpired(LocalDateTime expiresAt){
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public Authentication getAuthentication(String apiKeyRequest){

        if(apiKeyRequest == null  || apiKeyRequest.length() < 10){
            throw new BadCredentialsException("Invalid API Key");
        }

        String prefix = apiKeyRequest.substring(0,9);
        Optional<ApiKeys> apiKeyOptional = apiKeyRepository.findByApiKeyStartingWith(prefix);

        ApiKeys apiKey = apiKeyOptional.orElseThrow(
                () -> new BadCredentialsException("Invalid API Key")
        );

        if(!myPasswordEncoder.matches(apiKeyRequest.substring(9), apiKey.getApiKey().substring(9))){
            throw new BadCredentialsException("Invalid API Key");
        }

        if(!apiKey.isActive()){
            throw new BadCredentialsException("API Key is not active");
        }

        if(isApiKeyExpired(apiKey.getExpiresAt())){
            throw new CredentialsExpiredException("API Key expired");
        }

        return new ApiKeyAuthentication(apiKey.getApiKey(), AuthorityUtils.NO_AUTHORITIES);
    }
}
