package com.messmate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record StudentRegistrationRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Roll number is required")
        @Pattern(
                regexp = "^[A-Za-z0-9]{7}$",
                message = "Roll number must contain exactly 7 letters and numbers"
        )
        String rollNumber,

        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^[0-9]{10}$",
                message = "Phone number must contain exactly 10 digits"
        )
        String phone,

        @NotBlank(message = "Room number is required")
        String roomNo,

        @NotBlank(message = "Hostel block is required")
        @Pattern(
                regexp = "^[A-Fa-f]$",
                message = "Hostel block must be between A and F"
        )
        String block,

        @NotBlank(message = "Department is required")
        String department,

        @NotBlank(message = "Password is required")
        String password
) {
}