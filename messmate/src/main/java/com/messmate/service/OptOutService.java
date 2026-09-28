package com.messmate.service;

import com.messmate.model.Meal;
import com.messmate.model.MealOptOut;
import com.messmate.model.Resident;
import com.messmate.repository.MealOptOutRepository;
import com.messmate.repository.MealRepository;
import com.messmate.repository.ResidentRepository;
import org.springframework.stereotype.Service;

@Service
public class OptOutService {

    private final MealOptOutRepository optOutRepository;
    private final ResidentRepository residentRepository;
    private final MealRepository mealRepository;

    public OptOutService(
            MealOptOutRepository optOutRepository,
            ResidentRepository residentRepository,
            MealRepository mealRepository) {

        this.optOutRepository = optOutRepository;
        this.residentRepository = residentRepository;
        this.mealRepository = mealRepository;
    }

    public MealOptOut optOut(Long residentId, Long mealId) {

        Resident resident = residentRepository.findById(residentId)
                .orElseThrow(() ->
                        new RuntimeException("Resident not found"));

        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() ->
                        new RuntimeException("Meal not found"));

        if (optOutRepository.existsByResidentIdAndMealId(
                residentId, mealId)) {

            throw new IllegalArgumentException(
                    "Resident has already opted out");
        }

        return optOutRepository.save(
                new MealOptOut(resident, meal));
    }

    public void cancel(Long residentId, Long mealId) {

        if (!optOutRepository.existsByResidentIdAndMealId(
                residentId, mealId)) {

            throw new IllegalArgumentException(
                    "Opt-out record not found");
        }

        optOutRepository.deleteByResidentIdAndMealId(
                residentId, mealId);
    }
}