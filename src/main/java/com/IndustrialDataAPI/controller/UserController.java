package com.IndustrialDataAPI.controller;

import com.IndustrialDataAPI.exception.JWTNotValidException;
import com.IndustrialDataAPI.exception.NullOrEmptyParametersException;
import com.IndustrialDataAPI.model.Users;
import com.IndustrialDataAPI.service.JWTService;
import com.IndustrialDataAPI.service.MyUserDetailsService;
import com.IndustrialDataAPI.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("industrial")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ApplicationContext applicationContext;

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @PostMapping("/login")
    public ResponseEntity<String> loginUser (@RequestBody Users user){
        String jwtToken;
        LOGGER.info("Log in User");
        if(user == null || user.getEmail() == null || user.getPassword() == null) throw new NullOrEmptyParametersException();
        jwtToken = userService.verifyUser(user);
        LOGGER.info("User logged. Status - " + HttpStatus.OK);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers(@RequestHeader("Authorization") String authHeader){
        LOGGER.info("Getting all users");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) throw new NullOrEmptyParametersException();

        String jwtToken = authHeader.substring(7);
        String email = jwtService.extractEmail(jwtToken);
        UserDetails userDetails = applicationContext.getBean(MyUserDetailsService.class).loadUserByUsername(email);
        boolean isValid = jwtService.validateToken(jwtToken, userDetails);

        if (!isValid)throw new JWTNotValidException();

        List<Users> user = userService.findAllUsers();

        if (user.isEmpty()) throw new NullOrEmptyParametersException();

        LOGGER.info(HttpStatus.OK);
        return new ResponseEntity<List<Users>>(user, HttpStatus.OK);
    }

}
