package com.messmate.dto;

public record AdminResponse(
        Long id,
        String username,
        String role
) {
}