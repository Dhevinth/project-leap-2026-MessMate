package com.messmate.service;

import com.messmate.dto.AdminResponse;
import com.messmate.model.AdminUser;
import com.messmate.repository.AdminUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthService {

    private final AdminUserRepository repository;

    public AdminAuthService(AdminUserRepository repository) {
        this.repository = repository;
    }

    public AdminResponse login(String username, String password) {

        AdminUser admin = repository.findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid administrator username"
                        ));

        if (!admin.getPassword().equals(password)) {
            throw new IllegalArgumentException(
                    "Invalid administrator password"
            );
        }

        return new AdminResponse(
                admin.getId(),
                admin.getUsername(),
                admin.getRole()
        );
    }
}