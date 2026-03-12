package com.IndustrialDataAPI.service;

import com.IndustrialDataAPI.model.Machines;
import com.IndustrialDataAPI.repository.MachineRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class MachineService {

    private static final Logger LOGGER = LogManager.getLogger(MachineService.class);

    @Autowired
    private MachineRepository machineRepository;

    public Machines getMachineById(Long machineId){
        return machineRepository.getReferenceById(machineId);
    }

    public Machines saveMachine(String machineName, String ip, String area){
        return machineRepository.save(new Machines(machineName, ip, area));
    }
}
