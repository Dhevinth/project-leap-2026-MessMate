package com.messmate.repository;

import com.messmate.model.MealOptOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealOptOutRepository extends JpaRepository<MealOptOut, Long> {

    boolean existsByResidentIdAndMealId(Long residentId, Long mealId);

    void deleteByResidentIdAndMealId(Long residentId, Long mealId);
}