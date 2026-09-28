package com.messmate.controller;

import com.messmate.model.Resident;
import com.messmate.service.AuthService;
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
    public ResponseEntity<Resident> login(
            @RequestBody LoginRequest request) {

        Resident resident = authService.login(
                request.rollNumber(),
                request.password()
        );

        return ResponseEntity.ok(resident);
    }

    @GetMapping("/student/{rollNumber}")
    public ResponseEntity<Resident> getStudent(
            @PathVariable
            @Pattern(
                    regexp = "^[A-Za-z0-9]{7}$",
                    message = "Roll number must contain exactly 7 letters and numbers"
            )
            String rollNumber) {

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