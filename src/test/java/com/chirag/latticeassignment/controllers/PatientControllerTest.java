package com.chirag.latticeassignment.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.chirag.latticeassignment.entities.Patient;
import com.chirag.latticeassignment.services.PatientService;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {
    
    private MockMvc mockMvc;
    
    @Mock 
    private PatientService patientService;

    private PatientController patientController;

    @BeforeEach
    public void setup(){
        patientController = new PatientController(patientService);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    public void testCreatePatient() throws Exception{
        Patient patient = new Patient(1L, "Chirag Sardana", "Delhi", "Chiragsardana12@gmail.com", "8684076590", "Back Pain");
        String jsonFormat = "{                           " +
            "  \"id\": 1,                                " + 
            "  \"name\": \"Chirag Sardana\",             " +
            "  \"city\": \"Delhi\",                      " +
            "  \"email\": \"Chiragsardana12@gmail.com\", " +
            "  \"phoneNumber\": \"8684076590\",         " +
            "  \"symptom\": \"Back Pain\"            " +
        "   }                                               " ;


        when(patientService.createPatient(any(Patient.class))).thenReturn(patient);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/patients")
        .contentType("application/json")
        .content(jsonFormat))
        .andExpect(MockMvcResultMatchers.status().isOk());
        
    }

    @Test
    public void testGetAllPatient() throws Exception{
        Patient patient = new Patient(1L, "Chirag Sardana", "Delhi", "Chiragsardana12@gmail.com", "8684076590", "Back Pain");
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        when(patientService.getAllPatients()).thenReturn(patients);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
    }

    @Test 
    public void testGetPatientByIdPatientExist() throws Exception{
        Patient patient = new Patient(1L, "Chirag Sardana", "Delhi", "Chiragsardana12@gmail.com", "8684076590", "Back Pain");
        when(patientService.getPatientById(anyLong())).thenReturn(patient);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients/{id}", 1))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test 
    public void testGetPatientByIdPatientNotExist() throws Exception{
        // Patient patient = new Patient(1L, "Chirag Sardana", "Delhi", "Chiragsardana12@gmail.com", "8684076590", "Back Pain");
        // when(patientService.getPatientById(anyLong())).thenReturn(patient);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients/{id}", 1))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeletePatient() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/patients/{id}", 1))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
