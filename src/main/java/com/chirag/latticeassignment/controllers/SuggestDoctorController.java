package com.chirag.latticeassignment.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chirag.latticeassignment.entities.Doctor;
import com.chirag.latticeassignment.exceptions.InvalidCityException;
import com.chirag.latticeassignment.exceptions.PatientNotFoundException;
import com.chirag.latticeassignment.services.SuggestDoctorService;

@RestController
@RequestMapping("/api/suggest-doctors")
public class SuggestDoctorController {

    private SuggestDoctorService suggestDoctorService;

    public SuggestDoctorController(SuggestDoctorService suggestDoctorService) {
        this.suggestDoctorService = suggestDoctorService;
    }

    @GetMapping
    public ResponseEntity<?> suggestDoctors(@RequestParam Long patientId) throws Exception {
        List<Doctor> suggestedDoctors;

        try{
            suggestedDoctors = suggestDoctorService.suggestDoctors(patientId);
        }catch (PatientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }catch (InvalidCityException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

        if (suggestedDoctors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("There isn't any doctor present at your location for your symptom");
        }

        return ResponseEntity.ok(suggestedDoctors);
    }




   
}

