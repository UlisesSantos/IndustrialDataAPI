package com.IndustrialDataAPI.controller;

import com.IndustrialDataAPI.model.MachineRequest;
import com.IndustrialDataAPI.model.Machines;
import com.IndustrialDataAPI.service.ApiKeyService;
import com.IndustrialDataAPI.service.JWTService;
import com.IndustrialDataAPI.service.MachineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MachineController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MachineControllerTest {

    @MockitoBean
    private ApiKeyService apiKeyService;

    @MockitoBean
    private JWTService jwtService;

    @MockitoBean
    private MachineService machineService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //TODO: There is an error in the variable authentication because the filters are disabled.

    @Test
    void ShouldSaveOneMachine() throws Exception {
        MachineRequest machineRequest =
                new MachineRequest("machineName", "192.000.000", "area");
        Machines machine =
                new Machines(1L, "machineName", "192.000.000", "area");

        when(machineService.saveMachine(
                machineRequest.getMachine_name(),
                machineRequest.getIp(),
                machineRequest.getArea()))
                .thenReturn(machine);

        mockMvc.perform(post("/industrial/createMachine")
                        .content(objectMapper.writeValueAsString(machineRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.machine_id").value(1))
                .andExpect(jsonPath("$.machineName").value("machineName"))
                .andExpect(jsonPath("$.machineIp").value("192.000.000"))
                .andExpect(jsonPath("$.machineArea").value("area"));
    }
}
