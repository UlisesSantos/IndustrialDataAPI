package com.IndustrialDataAPI.repository;

import com.IndustrialDataAPI.model.ApiKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKeys, Long> {

    Optional<ApiKeys> findByApiKeyStartingWith(String apiKey);
}
