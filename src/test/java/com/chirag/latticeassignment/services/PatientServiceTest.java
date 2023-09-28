package com.chirag.latticeassignment.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.chirag.latticeassignment.entities.Patient;
import com.chirag.latticeassignment.repositories.PatientRepository;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @BeforeEach
    public void setup(){
        patientService = new PatientService(patientRepository);
    }

    @Test
    public void testCreatePatient(){
        Patient patient = new Patient(1L, "Chirag Sardana", "Sirsa", "Chiragsardana12@gmail.com", "8684076590", "Eye Pain");
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient returnPatient = patientService.createPatient(patient);
        assertEquals(patient.toString(), returnPatient.toString(), "The both Patient are different!");
    }

    @Test
    public void testGetAllPatient(){
        Patient patient = new Patient(1L, "Chirag Sardana", "Sirsa", "Chiragsardana12@gmail.com", "8684076590", "Eye Pain");
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        when(patientRepository.findAll()).thenReturn(patients);
        List<Patient> returnPatients = patientService.getAllPatients();
        assertEquals(patients.get(0).toString(), returnPatients.get(0).toString(), "The Patient List are not sane!");

    }

    @Test
    public void testGetPatientById(){
        Patient patient = new Patient(1L, "Chirag Sardana", "Sirsa", "Chiragsardana12@gmail.com", "8684076590", "Eye Pain");
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
        Patient returnPatient = patientService.getPatientById(1L);
        assertEquals(patient.getId(), returnPatient.getId(), "The Patient Id is different!");

    }

    @Test
    public void testDeletePatient(){
        patientService.deletePatient(1L);
        verify(patientRepository, times(1)).deleteById(1L);

    }
}
