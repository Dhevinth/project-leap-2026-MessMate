package com.messmate.controller;

import com.messmate.dto.StudentResponse;
import com.messmate.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/student/login")
    public ResponseEntity<StudentResponse> login(
            @Valid @RequestBody LoginRequest request) {

        StudentResponse student = authService.login(
                request.rollNumber(),
                request.password()
        );

        return ResponseEntity.ok(student);
    }

    @GetMapping("/student/{rollNumber}")
    public ResponseEntity<StudentResponse> getStudent(
            @PathVariable String rollNumber) {

        return ResponseEntity.ok(
                authService.getStudent(rollNumber)
        );
    }

    public record LoginRequest(

            @NotBlank(message = "Roll number is required")
            @Pattern(
                    regexp = "^[A-Za-z0-9]{7}$",
                    message = "Roll number must contain exactly 7 letters and numbers"
            )
            String rollNumber,

            @NotBlank(message = "Password is required")
            String password
    ) {
    }
}