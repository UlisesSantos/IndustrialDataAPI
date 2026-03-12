package com.IndustrialDataAPI.controller;

import com.IndustrialDataAPI.exception.DuplicatedEntryException;
import com.IndustrialDataAPI.model.MachineRequest;
import com.IndustrialDataAPI.model.Machines;
import com.IndustrialDataAPI.service.MachineService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("industrial")
public class MachineController {

    private static final Logger LOGGER = LogManager.getLogger(MachineController.class);

    @Autowired
    private MachineService machineService;

    @PostMapping("/machine")
    public ResponseEntity<Machines> createMachine(@RequestBody MachineRequest request, Authentication authentication){
        String email = authentication.getName();
        Machines machine;
        LOGGER.info("Attempt to register Machine by: {}", email);

        try {
            machine = machineService.saveMachine(request.getMachine_name(),
                    request.getIp(),
                    request.getArea());
        }catch (DataIntegrityViolationException e){
            LOGGER.error("Duplicate entry: {}", e.getMessage());
            throw new DuplicatedEntryException();
        }

        LOGGER.info("Machine registered by: {}", email);
        return ResponseEntity.ok(machine);
    }
}
