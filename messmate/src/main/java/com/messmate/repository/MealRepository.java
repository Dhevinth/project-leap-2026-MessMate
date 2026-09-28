package com.messmate.repository;

import com.messmate.model.Meal;
import com.messmate.model.MealType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findByMealDateBetween(
            LocalDate from,
            LocalDate to
    );

    boolean existsByMealDateAndMealType(
            LocalDate mealDate,
            MealType mealType
    );

    boolean existsByMealDateAndMealTypeAndIdNot(
            LocalDate mealDate,
            MealType mealType,
            Long id
    );
}