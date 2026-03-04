package com.IndustrialDataAPI.service;

import com.IndustrialDataAPI.model.Users;
import com.IndustrialDataAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    public List<Users> findAllUsers(){
        return userRepository.findAll();
    }

    public String verifyUser(Users users){
        String jwtToken = "";
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getEmail(),users.getPassword()));
        if(authentication.isAuthenticated()){
            jwtToken = jwtService.generateToken(users.getEmail());
        }
        return jwtToken;
    }
}
