package com.chirag.latticeassignment.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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

import com.chirag.latticeassignment.entities.Doctor;
import com.chirag.latticeassignment.repositories.DoctorRepository;


@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {
    private DoctorService doctorService;
    
    @Mock
    private DoctorRepository doctorRepository;

    @BeforeEach
    public void setup(){
        doctorService = new DoctorService(doctorRepository);
    }    

    @Test
    public void testCreateDoctor(){
        Doctor doctor = new Doctor(1L, "Chirag Sardana", "Sirsa", "chiragsardana12@gmail.com", "8684076590", "Coder");
        when(doctorRepository.save(doctor)).thenReturn(doctor);
        Doctor returnDoctor = doctorService.createDoctor(doctor);

        verify(doctorRepository, times(1)).save(any(Doctor.class));
        assertEquals(doctor.toString(), returnDoctor.toString(), "The Two Objects are not Equal!");

    }

    @Test
    public void testGetAllDoctor(){
        Doctor doctor = new Doctor(1L, "Chirag Sardana", "Sirsa", "chiragsardana12@gmail.com", "8684076590", "Coder");
        List<Doctor> list = new ArrayList<>();
        list.add(doctor);
        when(doctorRepository.findAll()).thenReturn(list);

        List<Doctor> returnList = doctorService.getAllDoctors();
        assertEquals(list.size(), returnList.size(), "Sizes are different!");
        assertEquals(list.get(0).toString(), list.get(0).toString(),  "Objects Value are different!");
    }

    @Test
    public void testGetDoctorById(){
        Doctor doctor = new Doctor(1L, "Chirag Sardana", "Sirsa", "chiragsardana12@gmail.com", "8684076590", "Coder");
        when(doctorRepository.findById(anyLong())).thenReturn(Optional.of(doctor));
        Doctor returnDoctor = doctorService.getDoctorById(1L);
        assertEquals(doctor.getId(), returnDoctor.getId(), "Id is Different!");
    }

    @Test
    public void testDeleteDoctor(){
        doctorService.deleteDoctor(1L);
        verify(doctorRepository, times(1)).deleteById(1L);
    }
    
}
