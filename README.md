# Java - Backend APIs for Doctor-Patient Platform

## Task Description

In this technical assignment, we are tasked with developing backend APIs for a platform where doctors can register their patients through a mobile or web portal. The core functionalities include:

1. Adding a doctor and specifying their specialty.
2. Adding a patient and recording their symptom.
3. Suggesting a doctor based on a patient's symptom.

## Doctor's Entity

In our database, the doctor's entity will include the following fields:

- Doctor's Name (at least 3 characters)
- City (max 20 characters, values: Delhi, Noida, Faridabad)
- Email (valid email address)
- Phone Number (at least 10 numbers)
- Speciality (values: Orthopedic, Gynecology, Dermatology, ENT specialist)

A doctor can be added or removed from the platform.

## Patient's Entity

In our database, the patient's entity will include the following fields:

- Patient's Name (at least 3 characters)
- City (any value)
- Email (valid email address)
- Phone Number (at least 10 numbers)
- Symptom (values: Arthritis, Back Pain, Tissue injuries, Dysmenorrhea, Skin infection, skin burn, Ear pain)

A patient can be added or removed from the platform.

## Suggesting Doctors API

We need to implement an API that accepts a patient ID and suggests doctors based on the patient's location and symptom. The logic should work as follows:

- If the patient's symptom matches a doctor's specialty in the same location, suggest that doctor.
- If there are no doctors in the patient's location (outside Delhi, Noida, Faridabad), respond with "We are still waiting to expand to your location."
- If there are no doctors with the required specialty in the patient's location for their symptom, respond with "There isn't any doctor present at your location for your symptom."

## Implementation Details

To achieve this task, we will use the following technologies:

- **Spring Boot Framework**: For core functionalities.
- **Hibernate**: For carrying out database operations.
- **Swagger**: Optional, but recommended for documenting and listing all APIs.

## Edge Cases

1. Edge-Case 1: If there are no doctors in the patient's location (i.e., outside Delhi, Noida, Faridabad), the response should be "We are still waiting to expand to your location."

2. Edge-Case 2: If there are no doctors for the patient's symptom in their location, the response should be "There isn't any doctor present at your location for your symptom."
