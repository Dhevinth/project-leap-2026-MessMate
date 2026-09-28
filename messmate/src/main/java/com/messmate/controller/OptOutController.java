package com.messmate.controller;

import com.messmate.model.MealOptOut;
import com.messmate.service.OptOutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/opt-outs")
public class OptOutController {

    private final OptOutService service;

    public OptOutController(OptOutService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MealOptOut> optOut(
            @RequestParam Long residentId,
            @RequestParam Long mealId) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.optOut(residentId, mealId));
    }

    @DeleteMapping
    public ResponseEntity<Void> cancel(
            @RequestParam Long residentId,
            @RequestParam Long mealId) {

        service.cancel(residentId, mealId);

        return ResponseEntity.noContent().build();
    }
}