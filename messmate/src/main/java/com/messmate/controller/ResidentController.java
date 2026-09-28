package com.messmate.controller;

import com.messmate.model.Resident;
import com.messmate.service.ResidentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    private final ResidentService service;

    public ResidentController(ResidentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Resident> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Resident getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<Resident> create(
            @Valid @RequestBody Resident resident) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(resident));
    }

    @PutMapping("/{id}")
    public Resident update(
            @PathVariable Long id,
            @Valid @RequestBody Resident resident) {

        return service.update(id, resident);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}