package com.IndustrialDataAPI.repository;

import com.IndustrialDataAPI.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Transactional(readOnly = true)
    Optional<Users> findUserByEmail(String email);
}
