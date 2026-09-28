package com.messmate.service;

import com.messmate.model.Meal;
import com.messmate.model.MenuItem;
import com.messmate.repository.MealRepository;
import com.messmate.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final MenuItemRepository menuItemRepository;

    public MealService(MealRepository mealRepository,
                       MenuItemRepository menuItemRepository) {
        this.mealRepository = mealRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<Meal> getMeals(LocalDate from, LocalDate to) {
        return mealRepository.findByMealDateBetweenOrderByMealDateAscMealTypeAsc(from, to);
    }

    public Meal getById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
    }

    public Meal create(Meal meal, List<Long> menuItemIds) {

        if (mealRepository.existsByMealDateAndMealType(
                meal.getMealDate(), meal.getMealType())) {
            throw new IllegalArgumentException(
                    "Meal already exists for this date and meal type"
            );
        }

        if (menuItemIds != null && !menuItemIds.isEmpty()) {
            List<MenuItem> items = menuItemRepository.findAllById(menuItemIds);
            meal.setMenuItems(items);
        }

        return mealRepository.save(meal);
    }

    public Meal update(Long id, Meal data, List<Long> menuItemIds) {

        Meal meal = getById(id);

        meal.setMealDate(data.getMealDate());
        meal.setMealType(data.getMealType());

        if (menuItemIds != null) {
            List<MenuItem> items = menuItemRepository.findAllById(menuItemIds);
            meal.setMenuItems(items);
        }

        return mealRepository.save(meal);
    }

    public void delete(Long id) {
        Meal meal = getById(id);
        mealRepository.delete(meal);
    }
}