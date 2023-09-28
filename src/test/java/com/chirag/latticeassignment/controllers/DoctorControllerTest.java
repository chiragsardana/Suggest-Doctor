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

import com.chirag.latticeassignment.entities.Doctor;
import com.chirag.latticeassignment.services.DoctorService;

@ExtendWith(MockitoExtension.class)
public class DoctorControllerTest {
    private MockMvc mockMvc;

    @Mock
    private DoctorService doctorService;

    private DoctorController doctorController;

    @BeforeEach
    public void setup() {
        doctorController = new DoctorController(doctorService);
        mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
    }

    @Test
    public void testCreateDoctor() throws Exception {
        Doctor doctor = new Doctor(1L, "Chirag Sardana", "Delhi", "Chiragsardana12@gmail.com", "8684076590", "Orthopedic");
        when(doctorService.createDoctor(any(Doctor.class))).thenReturn(doctor);
        System.out.println(doctor);
        String jsonFormat = "{                           " +
            "  \"id\": 1,                                " + 
            "  \"name\": \"Chirag Sardana\",             " +
            "  \"city\": \"Delhi\",                      " +
            "  \"email\": \"Chiragsardana12@gmail.com\", " +
            "  \"phoneNumber\": \"8684076590\",         " +
            "  \"speciality\": \"Orthopedic\"            " +
        "   }                                               " ;
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/doctors").contentType("application/json").content(jsonFormat))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test 
    public void testGetAllDoctor() throws Exception{
        Doctor doctor = new Doctor(1L, "Chirag Sardana", "Delhi", "Chiragsardana12@gmail.com", "8684076590", "Orthopedic");
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        when(doctorService.getAllDoctors()).thenReturn(doctors);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/doctors"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
    }


    @Test
    public void testGetDoctorById() throws Exception{
        Doctor doctor = new Doctor(1L, "Chirag Sardana", "Delhi", "Chiragsardana12@gmail.com", "8684076590", "Orthopedic");
        when(doctorService.getDoctorById(anyLong())).thenReturn(doctor);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/doctors/{id}", 1L))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(doctor.getId()));
    }

    @Test
    public void testDeleteDoctor() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/doctors/{id}", 1L))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
