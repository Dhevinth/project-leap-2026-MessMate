package com.messmate.dto;

public record StudentResponse(
        Long id,
        String name,
        String rollNumber,
        String phone,
        String roomNo,
        String block,
        String department
) {
}