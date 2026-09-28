package com.messmate.controller;

import com.messmate.model.Meal;
import com.messmate.model.MealType;
import com.messmate.service.MealService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService service;

    public MealController(MealService service) {
        this.service = service;
    }

    @GetMapping
    public List<Meal> getMeals(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {

        return service.getMeals(from, to);
    }

    @GetMapping("/{id}")
    public Meal getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<Meal> create(
            @Valid @RequestBody MealRequest request) {

        Meal meal = new Meal(
                request.mealDate(),
                request.mealType()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(meal, request.menuItemIds()));
    }

    @PutMapping("/{id}")
    public Meal update(
            @PathVariable Long id,
            @Valid @RequestBody MealRequest request) {

        Meal meal = new Meal(
                request.mealDate(),
                request.mealType()
        );

        return service.update(id, meal, request.menuItemIds());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    public record MealRequest(
            @NotNull LocalDate mealDate,
            @NotNull MealType mealType,
            List<Long> menuItemIds
    ) {
    }
}