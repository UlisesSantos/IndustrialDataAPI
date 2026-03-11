package com.IndustrialDataAPI.controller;

import com.IndustrialDataAPI.model.Users;
import com.IndustrialDataAPI.service.ApiKeyService;
import com.IndustrialDataAPI.service.JWTService;
import com.IndustrialDataAPI.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private ApiKeyService apiKeyService;

    @MockitoBean
    private JWTService jwtService;

    @Test
    void shouldReturnUsers() throws Exception{
        List<Users> users = List.of(new Users(), new Users(), new Users());
        when(userService.findAllUsers())
                .thenReturn(users);

        mockMvc.perform(get("/industrial/users"))
                .andExpect(status().isOk());

        verify(userService).findAllUsers();
    }

    @Test
    void shouldReturnJwtToken() throws Exception {
        Users user = new Users();
        user.setEmail("email");
        user.setPassword("password");

        when(userService.authenticateUser(user.getEmail(), user.getPassword())).thenReturn("token");

        mockMvc.perform(post("/industrial/login")
                        .content("""
                                    {
                                        "email": "email",
                                        "password": "password"
                                    }
                                """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("token"));

        verify(userService).authenticateUser(user.getEmail(), user.getPassword());
    }
}
