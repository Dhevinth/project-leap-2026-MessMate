package com.messmate.controller;

import com.messmate.model.Feedback;
import com.messmate.service.FeedbackService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService service;

    public FeedbackController(FeedbackService service) {
        this.service = service;
    }

    @GetMapping
    public List<Feedback> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<Feedback> create(
            @RequestBody FeedbackRequest request) {

        Feedback feedback = service.create(
                request.residentId(),
                request.mealId(),
                request.rating(),
                request.comment()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(feedback);
    }

    public record FeedbackRequest(
            @NotNull Long residentId,
            @NotNull Long mealId,
            @NotNull @Min(1) @Max(5) Integer rating,
            @Size(max = 500) String comment
    ) {
    }
}