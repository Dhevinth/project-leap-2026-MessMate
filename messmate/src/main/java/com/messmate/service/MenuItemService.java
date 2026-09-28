package com.messmate.service;

import com.messmate.model.MenuItem;
import com.messmate.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository repository;

    public MenuItemService(MenuItemRepository repository) {
        this.repository = repository;
    }

    public List<MenuItem> getAll() {
        return repository.findAll();
    }

    public MenuItem getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
    }

    public MenuItem create(MenuItem item) {
        return repository.save(item);
    }

    public MenuItem update(Long id, MenuItem data) {
        MenuItem item = getById(id);

        item.setName(data.getName());
        item.setCategory(data.getCategory());

        return repository.save(item);
    }

    public void delete(Long id) {
        MenuItem item = getById(id);
        repository.delete(item);
    }
}