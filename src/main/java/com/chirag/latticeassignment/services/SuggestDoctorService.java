package com.chirag.latticeassignment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chirag.latticeassignment.entities.Doctor;
import com.chirag.latticeassignment.entities.Patient;
import com.chirag.latticeassignment.exceptions.InvalidCityException;
import com.chirag.latticeassignment.exceptions.PatientNotFoundException;
import com.chirag.latticeassignment.repositories.DoctorRepository;
import com.chirag.latticeassignment.repositories.PatientRepository;
import com.chirag.latticeassignment.utilities.SuggestDoctorUtils;

@Service
public class SuggestDoctorService {

    private DoctorRepository doctorRepository;

    private PatientRepository patientRepository;

    private SuggestDoctorUtils suggestDoctorUtils;


    public SuggestDoctorService(DoctorRepository doctorRepository, PatientRepository patientRepository, SuggestDoctorUtils suggestDoctorUtils) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.suggestDoctorUtils = suggestDoctorUtils;
    }


    public List<Doctor> suggestDoctors(Long patientId) throws Exception {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if (!patientOptional.isPresent()) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found.");
        }

        Patient patient = patientOptional.get();
        String symptom = patient.getSymptom();
        String patientCity = patient.getCity();

        // Validate patient's city
        if (!suggestDoctorUtils.isValidCity(patientCity)) {
            throw new InvalidCityException("We are still waiting to expand to your location");
        }

        return doctorRepository.findByCityAndSpeciality(patientCity, suggestDoctorUtils.getSpecialityForSymptom(symptom));
    }
}
