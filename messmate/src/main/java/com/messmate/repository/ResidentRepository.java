package com.messmate.repository;

import com.messmate.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Long> {

    boolean existsByRoomNo(String roomNo);

    boolean existsByRollNumber(String rollNumber);

    Optional<Resident> findByRollNumber(String rollNumber);
}