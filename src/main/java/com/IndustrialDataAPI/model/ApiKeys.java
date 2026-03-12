package com.IndustrialDataAPI.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ApiKeys")
public class ApiKeys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long api_key_id;

    @Column(name = "api_key", nullable = false, unique = true)
    private String apiKey;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToOne
    @JoinColumn(name = "machine_id")
    private Machines machines;

    public ApiKeys(){}

    public ApiKeys(String apiKey, String serviceName, LocalDateTime createdAt, LocalDateTime expiresAt, boolean isActive, Machines machines) {
        this.apiKey = apiKey;
        this.serviceName = serviceName;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.isActive = isActive;
        this.machines = machines;
    }

    public ApiKeys(Long api_key_id, String apiKey, String serviceName, LocalDateTime createdAt, LocalDateTime expiresAt, boolean isActive, Machines machines) {
        this.api_key_id = api_key_id;
        this.apiKey = apiKey;
        this.serviceName = serviceName;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.isActive = isActive;
        this.machines = machines;
    }

    public Machines getMachines() {
        return machines;
    }

    public void setMachines(Machines machines) {
        this.machines = machines;
    }

    public Long getApi_key_id() {
        return api_key_id;
    }

    public void setApi_key_id(Long api_key_id) {
        this.api_key_id = api_key_id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
