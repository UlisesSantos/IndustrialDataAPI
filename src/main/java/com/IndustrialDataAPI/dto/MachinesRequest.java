package com.IndustrialDataAPI.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public class MachinesRequest {

    @NotBlank
    private String machine_name;

    @NotBlank
    private String ip;

    @NotBlank
    private String area;

    public MachinesRequest(String machine_name, String ip, String area) {
        this.machine_name = machine_name;
        this.ip = ip;
        this.area = area;
    }

    public String getMachine_name() {
        return machine_name;
    }

    public void setMachine_name(String machine_name) {
        this.machine_name = machine_name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
