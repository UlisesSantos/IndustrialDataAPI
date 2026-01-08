package com.IndustrialDataAPI.security;

import com.IndustrialDataAPI.model.Users;
import com.IndustrialDataAPI.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MyPasswordEncoder passwordEncoder;

    public AdminUserInitializer(UserRepository userRepository, MyPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args){
        if(userRepository.findUserByEmail("admin").isEmpty()){
            Users admin = new Users();
            admin.setEmail("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(admin);
        }
    }
}
