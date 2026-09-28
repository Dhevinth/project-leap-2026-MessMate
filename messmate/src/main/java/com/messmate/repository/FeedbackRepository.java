package com.messmate.repository;

import com.messmate.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    boolean existsByResidentIdAndMealId(Long residentId, Long mealId);

    @Query("""
        SELECT AVG(f.rating)
        FROM Feedback f
        WHERE f.meal.id = :mealId
    """)
    Double getAverageRatingByMeal(@Param("mealId") Long mealId);

    @Query("""
        SELECT AVG(f.rating)
        FROM Feedback f
        WHERE f.meal.mealDate = :date
    """)
    Double getAverageRatingByDay(@Param("date") LocalDate date);

    @Query("""
        SELECT f.meal, AVG(f.rating)
        FROM Feedback f
        WHERE f.meal.mealDate BETWEEN :from AND :to
        GROUP BY f.meal
        HAVING AVG(f.rating) <= :threshold
    """)
    List<Object[]> findLowRatedMeals(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to,
            @Param("threshold") double threshold
    );
}