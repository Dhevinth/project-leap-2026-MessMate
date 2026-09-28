package com.messmate.controller;

import com.messmate.dto.StudentRegistrationRequest;
import com.messmate.dto.StudentResponse;
import com.messmate.model.Resident;
import com.messmate.service.ResidentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/student")
public class StudentRegistrationController {

    private final ResidentService residentService;

    public StudentRegistrationController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping("/register")
    public ResponseEntity<StudentResponse> register(
            @Valid @RequestBody StudentRegistrationRequest request) {

        Resident student = new Resident(
                request.name(),
                request.rollNumber(),
                request.roomNo(),
                request.block(),
                request.department(),
                request.phone(),
                request.password()
        );

        Resident savedStudent = residentService.create(student);

        StudentResponse response = new StudentResponse(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getRollNumber(),
                savedStudent.getPhone(),
                savedStudent.getRoomNo(),
                savedStudent.getBlock(),
                savedStudent.getDepartment()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}