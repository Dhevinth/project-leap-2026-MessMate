package com.messmate.controller;

import com.messmate.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/average/meal/{mealId}")
    public Double averageByMeal(@PathVariable Long mealId) {

        return service.getAverageRating(mealId);
    }

    @GetMapping("/average/day")
    public Double averageByDay(
            @RequestParam LocalDate date) {

        return service.getAverageRatingByDay(date);
    }

    @GetMapping("/low-rated")
    public List<Object[]> lowRated(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to,
            @RequestParam(defaultValue = "3.0") double threshold) {

        return service.getLowRatedMeals(
                from,
                to,
                threshold
        );
    }
}