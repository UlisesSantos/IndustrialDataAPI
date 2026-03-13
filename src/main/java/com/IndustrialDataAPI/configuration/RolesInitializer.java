package com.IndustrialDataAPI.configuration;

import com.IndustrialDataAPI.model.Roles;
import com.IndustrialDataAPI.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class RolesInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args){
        String[] roles = {"ADMIN", "USER"};

        if(roleRepository.findAll().isEmpty()){
            for(int i = 0; i < roles.length; i++){
                roleRepository.save(new Roles(i + 1L, roles[i]));
            }
        }
    }
}
