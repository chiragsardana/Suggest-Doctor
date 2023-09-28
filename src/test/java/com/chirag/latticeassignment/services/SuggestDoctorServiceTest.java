package com.chirag.latticeassignment.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.chirag.latticeassignment.entities.Doctor;
import com.chirag.latticeassignment.entities.Patient;
import com.chirag.latticeassignment.exceptions.InvalidCityException;
import com.chirag.latticeassignment.exceptions.PatientNotFoundException;
import com.chirag.latticeassignment.repositories.DoctorRepository;
import com.chirag.latticeassignment.repositories.PatientRepository;
import com.chirag.latticeassignment.utilities.SuggestDoctorUtils;

@ExtendWith(MockitoExtension.class)
public class SuggestDoctorServiceTest {
    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    private SuggestDoctorUtils suggestDoctorUtils;
    
    private SuggestDoctorService suggestDoctorService;

    @BeforeEach
    public void setup(){
        suggestDoctorUtils = new SuggestDoctorUtils();
        suggestDoctorService = new SuggestDoctorService(doctorRepository, patientRepository, suggestDoctorUtils);
    }

    @Test
    public void testSuggestDoctors() throws Exception{
        Doctor doctor = new Doctor(1L, "Chirag Sardana", "Delhi", "chiragsardana12@gmail.com", "8684076590", "Coder");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        Patient patient = new Patient(1L, "Chirag Sardana", "Noida", "Chiragsardana12@gmail.com", "8684076590", "Eye Pain");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(doctorRepository.findByCityAndSpeciality(patient.getCity(), suggestDoctorUtils.getSpecialityForSymptom(patient.getSymptom()))).thenReturn(doctors);

        List<Doctor> returnDoctor = suggestDoctorService.suggestDoctors(patient.getId());


        assertEquals(doctors.size(), returnDoctor.size(), "The List Size is different!");

    }

    @Test
    public void testSuggestDoctorsPatientIdNotFound(){
        assertThrows(PatientNotFoundException.class,  () -> {
            suggestDoctorService.suggestDoctors(1L);
        }, "There is Already Patient with Patiend ID 1");
    }

    @Test
    public void testSuggestDoctorsInvalidCity(){

        Patient patient = new Patient(1L, "Chirag Sardana", "Sirsa", "Chiragsardana12@gmail.com", "8684076590", "Eye Pain");
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
         assertThrows(InvalidCityException.class,  () -> {
            suggestDoctorService.suggestDoctors(1L);
        }, "City is Existing in the DB");
    }
}
