package com.messmate.configuration;

import com.messmate.model.*;
import com.messmate.repository.MealRepository;
import com.messmate.repository.MenuItemRepository;
import com.messmate.repository.ResidentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataSeeder
{

    @Bean
    CommandLineRunner seed(
            ResidentRepository residentRepository,
            MenuItemRepository menuItemRepository,
            MealRepository mealRepository) {

        return args -> {

            if (residentRepository.count() == 0) {

                residentRepository.saveAll(List.of(
                        new Resident("Arun", "A101", "9876543210"),
                        new Resident("Kavin", "A102", "9876543211"),
                        new Resident("Dhevinth", "A103", "9876543212")
                ));
            }

            if (menuItemRepository.count() == 0) {

                menuItemRepository.saveAll(List.of(
                        new MenuItem("Idli", "South Indian"),
                        new MenuItem("Sambar", "Side Dish"),
                        new MenuItem("Rice", "Main Course"),
                        new MenuItem("Chicken Curry", "Non-Veg"),
                        new MenuItem("Chapati", "Main Course"),
                        new MenuItem("Vegetable Curry", "Veg")
                ));
            }

            if (mealRepository.count() == 0) {

                List<MenuItem> items = menuItemRepository.findAll();

                Meal breakfast = new Meal(
                        LocalDate.now(),
                        MealType.BREAKFAST
                );

                breakfast.setMenuItems(
                        items.subList(0, Math.min(2, items.size()))
                );

                Meal lunch = new Meal(
                        LocalDate.now(),
                        MealType.LUNCH
                );

                lunch.setMenuItems(
                        items.subList(2, Math.min(4, items.size()))
                );

                Meal dinner = new Meal(
                        LocalDate.now(),
                        MealType.DINNER
                );

                dinner.setMenuItems(
                        items.subList(
                                Math.min(4, items.size()),
                                items.size()
                        )
                );

                mealRepository.saveAll(
                        List.of(breakfast, lunch, dinner)
                );
            }
        };
    }
}