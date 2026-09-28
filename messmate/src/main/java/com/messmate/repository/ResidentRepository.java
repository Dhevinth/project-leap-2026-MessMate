package com.messmate.repository;

import com.messmate.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Long> {

    boolean existsByRoomNo(String roomNo);
}