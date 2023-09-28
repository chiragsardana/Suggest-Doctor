package com.chirag.latticeassignment.controllers;

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
import com.chirag.latticeassignment.exceptions.InvalidCityException;
import com.chirag.latticeassignment.services.SuggestDoctorService;

@ExtendWith(MockitoExtension.class)
public class SuggestDoctorControllerTest {
    @Mock
    private SuggestDoctorService suggestDoctorService;

    private MockMvc mockMvc;

    private SuggestDoctorController suggestDoctorController;

    @BeforeEach
    public void setup(){
        suggestDoctorController = new SuggestDoctorController(suggestDoctorService);
        mockMvc = MockMvcBuilders.standaloneSetup(suggestDoctorController).build();
    }

    @Test
    public void testSuggestDoctorsNoPatientWithSpecificId() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/suggest-doctors?patientId={id}", 1))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSuggestDoctorsInvalidCity() throws Exception{
        when(suggestDoctorService.suggestDoctors(anyLong())).thenThrow(new InvalidCityException("null"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/suggest-doctors?patientId={id}", 1))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testSuggestDoctorNoDoctorInYourLocation() throws Exception{
        List<Doctor> doctors = new ArrayList<>();
        when(suggestDoctorService.suggestDoctors(anyLong())).thenReturn(doctors);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/suggest-doctors?patientId={id}", 1))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.content().string("There isn't any doctor present at your location for your symptom"));

    }

}
