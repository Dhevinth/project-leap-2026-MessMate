package com.messmate.controller;

import com.messmate.model.Feedback;
import com.messmate.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<Feedback> create(
            @Valid @RequestBody Feedback feedback) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(feedbackService.create(feedback));
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAll() {
        return ResponseEntity.ok(
                feedbackService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                feedbackService.getById(id)
        );
    }
}