package com.messmate.service;

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
                        new RuntimeException("Resident not found"));
    }

    public Resident create(Resident resident) {
        if (repository.existsByRoomNo(resident.getRoomNo())) {
            throw new IllegalArgumentException(
                    "Room number already exists");
        }

        return repository.save(resident);
    }

    public Resident update(Long id, Resident data) {
        Resident resident = getById(id);

        resident.setName(data.getName());
        resident.setRoomNo(data.getRoomNo());
        resident.setPhone(data.getPhone());

        return repository.save(resident);
    }

    public void delete(Long id) {
        Resident resident = getById(id);
        repository.delete(resident);
    }
}