package com.IndustrialDataAPI.repository;

import com.IndustrialDataAPI.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

    boolean existsByRole(String role);

    Optional<Roles> findByRole(String role);
}
