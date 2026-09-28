package com.messmate.service;

import com.messmate.dto.StudentResponse;
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

    public StudentResponse login(String rollNumber, String password) {

        Resident student = residentRepository
                .findByRollNumber(rollNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student account not found"
                        ));

        if (!student.getPassword().equals(password)) {
            throw new IllegalArgumentException(
                    "Invalid password"
            );
        }

        return toResponse(student);
    }

    public StudentResponse getStudent(String rollNumber) {

        Resident student = residentRepository
                .findByRollNumber(rollNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student account not found"
                        ));

        return toResponse(student);
    }

    private StudentResponse toResponse(Resident student) {

        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getRollNumber(),
                student.getPhone(),
                student.getRoomNo(),
                student.getBlock(),
                student.getDepartment()
        );
    }
}