package com.messmate.repository;

import com.messmate.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findByMealDateBetweenOrderByMealDateAscMealTypeAsc(
            LocalDate from,
            LocalDate to
    );

    boolean existsByMealDateAndMealType(
            LocalDate mealDate,
            com.messmate.model.MealType mealType
    );
}