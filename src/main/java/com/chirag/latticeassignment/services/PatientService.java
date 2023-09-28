package com.chirag.latticeassignment.services;

import org.springframework.stereotype.Service;

import com.chirag.latticeassignment.entities.Patient;
import com.chirag.latticeassignment.repositories.PatientRepository;

import jakarta.validation.Valid;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(@Valid Patient patient) {
        // Add additional business logic, validation, or processing if needed
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    // Add additional methods for patient-related operations if needed
}
