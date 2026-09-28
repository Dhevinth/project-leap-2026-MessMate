package com.messmate.service;

import com.messmate.exception.ResourceNotFoundException;
import com.messmate.model.Resident;
import com.messmate.repository.ResidentRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final ResidentRepository residentRepository;

    public AuthService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public Resident login(String rollNumber, String password) {

        Resident resident = residentRepository.findByRollNumber(rollNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student account not found"
                        ));

        if (!resident.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

        return resident;
    }

    public Resident getStudent(String rollNumber) {

        return residentRepository.findByRollNumber(rollNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student account not found"
                        ));
    }
}