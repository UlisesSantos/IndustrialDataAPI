package com.IndustrialDataAPI.repository;

import com.IndustrialDataAPI.model.Machines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machines, Long> {

}
