package com.messmate.controller;

import com.messmate.model.AdminUser;
import com.messmate.service.AdminAuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/admin")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    public AdminAuthController(AdminAuthService adminAuthService) {
        this.adminAuthService = adminAuthService;
    }

    @PostMapping("/login")
    public ResponseEntity<AdminUser> login(
            @Valid @RequestBody LoginRequest request) {

        AdminUser admin = adminAuthService.login(
                request.username(),
                request.password()
        );

        return ResponseEntity.ok(admin);
    }

    public record LoginRequest(
            @NotBlank(message = "Username is required")
            String username,

            @NotBlank(message = "Password is required")
            String password
    ) {}
}