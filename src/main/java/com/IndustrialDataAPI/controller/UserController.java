package com.IndustrialDataAPI.controller;

import com.IndustrialDataAPI.model.LoginRequest;
import com.IndustrialDataAPI.model.Users;
import com.IndustrialDataAPI.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("industrial")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @PostMapping("/login")
    public ResponseEntity<String> loginUser (@Validated @RequestBody LoginRequest request){
        LOGGER.info("Login attempt for email=" + request.getEmail());

        String jwtToken = userService.authenticateUser(request.getEmail(), request.getPassword());

        LOGGER.info("Login successful for email=" + request.getEmail());
        return ResponseEntity.ok(jwtToken);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers(){
        LOGGER.info("Getting all users");
        List<Users> users = userService.findAllUsers();
        LOGGER.info("Returned " + users.size() + " Users");
        return ResponseEntity.ok(users);
    }
}
