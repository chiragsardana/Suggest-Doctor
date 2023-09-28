package com.chirag.latticeassignment.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.chirag.latticeassignment.entities.Doctor;

@DataJpaTest
// JPA-specific test
// provides some auto-configuration for testing with Spring Data JPA.
public class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void testFindByCityAndSpeciality(){
        Doctor doctor = new Doctor(1L, "Chirag Sardana", "Sirsa", "chiragsardana12@gmail.com", "8684076590", "Coder");
        doctorRepository.save(doctor);
        List<Doctor> doctors = doctorRepository.findByCityAndSpeciality("Sirsa", "Coder");
        assertEquals(doctors.size(), 1);
        assertEquals(doctor.toString(), doctors.get(0).toString(), "Objects are different!");
        assertTrue(!doctors.isEmpty(), "It is Empty!");
    }    
}
