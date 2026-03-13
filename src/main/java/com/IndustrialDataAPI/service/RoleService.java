package com.IndustrialDataAPI.service;

import com.IndustrialDataAPI.exception.RoleNotFoundException;
import com.IndustrialDataAPI.model.Roles;
import com.IndustrialDataAPI.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public boolean roleExist(String role){
        return roleRepository.existsByRole(role);
    }

    public Roles findRolesByRole(String role){
        Optional<Roles> rolesOptional = roleRepository.findByRole(role);
        return rolesOptional.orElseThrow(RoleNotFoundException:: new);
    }
}
