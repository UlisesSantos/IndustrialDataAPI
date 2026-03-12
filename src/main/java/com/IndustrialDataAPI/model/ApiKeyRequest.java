package com.IndustrialDataAPI.model;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public class ApiKeyRequest {

    @NotBlank
    private String service_name;

    @NotBlank
    private Long machine_id;

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public Long getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(Long machine_id) {
        this.machine_id = machine_id;
    }
}
