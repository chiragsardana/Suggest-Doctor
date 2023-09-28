package com.chirag.latticeassignment.services;

import org.springframework.stereotype.Service;

import com.chirag.latticeassignment.entities.Doctor;
import com.chirag.latticeassignment.repositories.DoctorRepository;

import jakarta.validation.Valid;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor createDoctor(@Valid Doctor doctor) {
        // Add additional business logic, validation, or processing if needed
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    // Add additional methods for doctor-related operations if needed
}
