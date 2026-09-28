package com.messmate.service;

import com.messmate.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    private final FeedbackRepository feedbackRepository;

    public ReportService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Double getAverageRating(Long mealId) {

        Double avg = feedbackRepository.getAverageRatingByMeal(mealId);

        return avg == null ? 0.0 : avg;
    }

    public Double getAverageRatingByDay(LocalDate date) {

        Double avg = feedbackRepository.getAverageRatingByDay(date);

        return avg == null ? 0.0 : avg;
    }

    public List<Object[]> getLowRatedMeals(
            LocalDate from,
            LocalDate to,
            double threshold) {

        return feedbackRepository.findLowRatedMeals(
                from,
                to,
                threshold
        );
    }
}