package com.IndustrialDataAPI.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Machines")
public class Machines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long machine_id;

    @Column(name = "machine_name", nullable = false, unique = true)
    private String machineName;

    @Column(name = "machine_ip", nullable = false, unique = true)
    private String machineIp;

    @Column(name = "machine_area", nullable = false)
    private String machineArea;

    public Machines(){}

    public Machines(String machineName, String machineIp, String machineArea) {
        this.machineName = machineName;
        this.machineIp = machineIp;
        this.machineArea = machineArea;
    }

    public Machines(Long machine_id, String machineName, String machineIp, String machineArea) {
        this.machine_id = machine_id;
        this.machineName = machineName;
        this.machineIp = machineIp;
        this.machineArea = machineArea;
    }

    public Long getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(Long machine_id) {
        this.machine_id = machine_id;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineIp() {
        return machineIp;
    }

    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp;
    }

    public String getMachineArea() {
        return machineArea;
    }

    public void setMachineArea(String machineArea) {
        this.machineArea = machineArea;
    }
}
