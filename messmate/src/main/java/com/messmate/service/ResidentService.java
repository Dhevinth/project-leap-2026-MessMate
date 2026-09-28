package com.messmate.service;

import com.messmate.exception.ResourceNotFoundException;
import com.messmate.model.Resident;
import com.messmate.repository.ResidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentService {

    private final ResidentRepository repository;

    public ResidentService(ResidentRepository repository) {
        this.repository = repository;
    }

    public List<Resident> getAll() {
        return repository.findAll();
    }

    public Resident getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Resident not found with id: " + id
                        ));
    }

    public Resident create(Resident resident) {

        if (repository.existsByRollNumber(resident.getRollNumber())) {
            throw new IllegalArgumentException(
                    "Roll number already exists"
            );
        }

        if (repository.existsByRoomNo(resident.getRoomNo())) {
            throw new IllegalArgumentException(
                    "Room number already exists"
            );
        }

        return repository.save(resident);
    }

    public Resident update(Long id, Resident data) {

        Resident resident = getById(id);

        if (!resident.getRollNumber().equals(data.getRollNumber())
                && repository.existsByRollNumber(data.getRollNumber())) {

            throw new IllegalArgumentException(
                    "Roll number already exists"
            );
        }

        if (!resident.getRoomNo().equals(data.getRoomNo())
                && repository.existsByRoomNo(data.getRoomNo())) {

            throw new IllegalArgumentException(
                    "Room number already exists"
            );
        }

        resident.setName(data.getName());
        resident.setRollNumber(data.getRollNumber());
        resident.setRoomNo(data.getRoomNo());
        resident.setBlock(data.getBlock());
        resident.setDepartment(data.getDepartment());
        resident.setPhone(data.getPhone());

        return repository.save(resident);
    }

    public void delete(Long id) {

        Resident resident = getById(id);

        repository.delete(resident);
    }
}