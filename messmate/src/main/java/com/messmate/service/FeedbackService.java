package com.messmate.service;

import com.messmate.exception.ResourceNotFoundException;
import com.messmate.model.Feedback;
import com.messmate.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback create(Feedback feedback) {

        if (feedback.getRating() < 1 ||
                feedback.getRating() > 5) {

            throw new IllegalArgumentException(
                    "Rating must be between 1 and 5"
            );
        }

        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }

    public Feedback getById(Long id) {

        return feedbackRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Feedback not found with id: " + id
                        ));
    }
}