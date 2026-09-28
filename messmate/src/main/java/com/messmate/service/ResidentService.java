package com.messmate.service;

import com.messmate.exception.ResourceNotFoundException;
import com.messmate.model.Resident;
import com.messmate.repository.ResidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentService {

    private final ResidentRepository residentRepository;

    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public List<Resident> getAll() {
        return residentRepository.findAll();
    }

    public Resident getById(Long id) {
        return residentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id
                        ));
    }

    public Resident create(Resident resident) {

        if (residentRepository.existsByRollNumber(
                resident.getRollNumber())) {

            throw new IllegalArgumentException(
                    "Roll number already registered"
            );
        }

        if (residentRepository.existsByRoomNo(
                resident.getRoomNo())) {

            throw new IllegalArgumentException(
                    "Room number already registered"
            );
        }

        return residentRepository.save(resident);
    }

    public Resident update(Long id, Resident updated) {

        Resident existing = getById(id);

        if (!existing.getRollNumber()
                .equals(updated.getRollNumber())
                && residentRepository.existsByRollNumber(
                updated.getRollNumber())) {

            throw new IllegalArgumentException(
                    "Roll number already registered"
            );
        }

        if (!existing.getRoomNo()
                .equals(updated.getRoomNo())
                && residentRepository.existsByRoomNo(
                updated.getRoomNo())) {

            throw new IllegalArgumentException(
                    "Room number already registered"
            );
        }

        existing.setName(updated.getName());
        existing.setRollNumber(updated.getRollNumber());
        existing.setRoomNo(updated.getRoomNo());
        existing.setBlock(updated.getBlock());
        existing.setDepartment(updated.getDepartment());
        existing.setPhone(updated.getPhone());

        return residentRepository.save(existing);
    }

    public void delete(Long id) {

        Resident resident = getById(id);

        residentRepository.delete(resident);
    }
}