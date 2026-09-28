package com.messmate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Entity
@Table(name = "residents")
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Roll number is required")
    @Pattern(
            regexp = "^[A-Za-z0-9]{7}$",
            message = "Roll number must contain exactly 7 letters and numbers"
    )
    @Column(nullable = false, unique = true, length = 7)
    private String rollNumber;

    @NotBlank(message = "Room number is required")
    @Column(nullable = false)
    private String roomNo;

    @NotBlank(message = "Block is required")
    @Column(nullable = false)
    private String block;

    @NotBlank(message = "Department is required")
    @Column(nullable = false)
    private String department;

    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone must contain exactly 10 digits"
    )
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Resident() {
        this.createdAt = LocalDateTime.now();
    }

    public Resident(String name,
                    String rollNumber,
                    String roomNo,
                    String block,
                    String department,
                    String phone,
                    String password) {

        this.name = name;
        this.rollNumber = rollNumber;
        this.roomNo = roomNo;
        this.block = block;
        this.department = department;
        this.phone = phone;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}