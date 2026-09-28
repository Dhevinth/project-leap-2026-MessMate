package com.messmate.repository;

import com.messmate.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByRatingLessThanEqual(Integer rating);

    List<Feedback> findByCreatedAtBetween(
            LocalDateTime from,
            LocalDateTime to
    );
}