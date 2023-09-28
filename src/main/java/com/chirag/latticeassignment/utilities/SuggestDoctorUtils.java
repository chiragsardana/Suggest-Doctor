package com.chirag.latticeassignment.utilities;

import org.springframework.stereotype.Component;

@Component
public class SuggestDoctorUtils {
    public boolean isValidCity(String city) {
        // Check if the city is one of the allowed values
        return "Delhi".equalsIgnoreCase(city) || "Noida".equalsIgnoreCase(city) || "Faridabad".equalsIgnoreCase(city);
    }

    public String getSpecialityForSymptom(String symptom) {
        // Map symptoms to doctor specialties based on your requirements
        switch (symptom) {
            case "Arthritis":
            case "Back Pain":
            case "Tissue injuries":
                return "Orthopedic";
            case "Dysmenorrhea":
                return "Gynecology";
            case "Skin infection":
            case "Skin burn":
                return "Dermatology";
            case "Ear pain":
                return "ENT specialist";
            default:
                return null;
        }
    }
}
