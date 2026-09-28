package com.messmate.service;

import com.messmate.model.Feedback;
import com.messmate.model.Meal;
import com.messmate.model.Resident;
import com.messmate.repository.FeedbackRepository;
import com.messmate.repository.MealRepository;
import com.messmate.repository.ResidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ResidentRepository residentRepository;
    private final MealRepository mealRepository;

    public FeedbackService(
            FeedbackRepository feedbackRepository,
            ResidentRepository residentRepository,
            MealRepository mealRepository) {

        this.feedbackRepository = feedbackRepository;
        this.residentRepository = residentRepository;
        this.mealRepository = mealRepository;
    }

    public Feedback create(
            Long residentId,
            Long mealId,
            Integer rating,
            String comment) {

        Resident resident = residentRepository.findById(residentId)
                .orElseThrow(() ->
                        new RuntimeException("Resident not found"));

        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() ->
                        new RuntimeException("Meal not found"));

        if (feedbackRepository.existsByResidentIdAndMealId(
                residentId, mealId)) {

            throw new IllegalArgumentException(
                    "Resident has already submitted feedback for this meal");
        }

        Feedback feedback =
                new Feedback(resident, meal, rating, comment);

        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAll() {
        return feedbackRepository.findAll();
    }

    public Double getAverageRating(Long mealId) {

        if (!mealRepository.existsById(mealId)) {
            throw new RuntimeException("Meal not found");
        }

        Double average =
                feedbackRepository.getAverageRatingByMeal(mealId);

        return average == null ? 0.0 : average;
    }
}