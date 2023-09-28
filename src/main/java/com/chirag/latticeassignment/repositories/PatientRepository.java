package com.chirag.latticeassignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chirag.latticeassignment.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Add custom query methods if needed
}
