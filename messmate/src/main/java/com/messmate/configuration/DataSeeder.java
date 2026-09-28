package com.messmate.configuration;

import com.messmate.model.AdminUser;
import com.messmate.model.Meal;
import com.messmate.model.MealType;
import com.messmate.model.MenuItem;
import com.messmate.model.Resident;
import com.messmate.repository.AdminUserRepository;
import com.messmate.repository.MealRepository;
import com.messmate.repository.MenuItemRepository;
import com.messmate.repository.ResidentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(
            ResidentRepository residentRepository,
            MenuItemRepository menuItemRepository,
            MealRepository mealRepository,
            AdminUserRepository adminUserRepository) {

        return args -> {

            // =========================
            // DEFAULT WARDEN ACCOUNT
            // =========================

            if (!adminUserRepository.existsByUsername("warden")) {

                AdminUser admin = new AdminUser();

                admin.setUsername("warden");
                admin.setPassword("warden123");
                admin.setRole("WARDEN");

                adminUserRepository.save(admin);
            }


            // =========================
            // SAMPLE STUDENTS
            // =========================

            if (residentRepository.count() == 0) {

                Resident r1 = new Resident();

                r1.setName("Arun Kumar");
                r1.setRollNumber("24CCE01");
                r1.setRoomNo("A101");
                r1.setBlock("A");
                r1.setDepartment("CCE");
                r1.setPhone("9876543210");
                r1.setPassword("student123");

                residentRepository.save(r1);


                Resident r2 = new Resident();

                r2.setName("Kavin Raj");
                r2.setRollNumber("24CSE02");
                r2.setRoomNo("B202");
                r2.setBlock("B");
                r2.setDepartment("CSE");
                r2.setPhone("9876543211");
                r2.setPassword("student123");

                residentRepository.save(r2);


                Resident r3 = new Resident();

                r3.setName("Dhevinth");
                r3.setRollNumber("24CCE03");
                r3.setRoomNo("C303");
                r3.setBlock("C");
                r3.setDepartment("CCE");
                r3.setPhone("9876543212");
                r3.setPassword("student123");

                residentRepository.save(r3);
            }


            // =========================
            // SAMPLE MENU ITEMS
            // =========================

            if (menuItemRepository.count() == 0) {

                menuItemRepository.save(
                        new MenuItem("Idli", "Breakfast")
                );

                menuItemRepository.save(
                        new MenuItem("Sambar", "Breakfast")
                );

                menuItemRepository.save(
                        new MenuItem("Rice", "Lunch")
                );

                menuItemRepository.save(
                        new MenuItem("Chicken Curry", "Lunch")
                );

                menuItemRepository.save(
                        new MenuItem("Chapati", "Dinner")
                );
            }


            // =========================
            // SAMPLE MEALS
            // =========================

            if (mealRepository.count() == 0) {

                LocalDate today = LocalDate.now();

                MenuItem idli =
                        menuItemRepository
                                .findAll()
                                .stream()
                                .filter(item ->
                                        item.getName()
                                                .equalsIgnoreCase("Idli"))
                                .findFirst()
                                .orElse(null);

                MenuItem sambar =
                        menuItemRepository
                                .findAll()
                                .stream()
                                .filter(item ->
                                        item.getName()
                                                .equalsIgnoreCase("Sambar"))
                                .findFirst()
                                .orElse(null);

                MenuItem rice =
                        menuItemRepository
                                .findAll()
                                .stream()
                                .filter(item ->
                                        item.getName()
                                                .equalsIgnoreCase("Rice"))
                                .findFirst()
                                .orElse(null);

                MenuItem chicken =
                        menuItemRepository
                                .findAll()
                                .stream()
                                .filter(item ->
                                        item.getName()
                                                .equalsIgnoreCase("Chicken Curry"))
                                .findFirst()
                                .orElse(null);

                MenuItem chapati =
                        menuItemRepository
                                .findAll()
                                .stream()
                                .filter(item ->
                                        item.getName()
                                                .equalsIgnoreCase("Chapati"))
                                .findFirst()
                                .orElse(null);


                // Breakfast

                Meal breakfast =
                        new Meal(
                                today,
                                MealType.BREAKFAST
                        );

                if (idli != null) {
                    breakfast.getMenuItems().add(idli);
                }

                if (sambar != null) {
                    breakfast.getMenuItems().add(sambar);
                }

                mealRepository.save(breakfast);


                // Lunch

                Meal lunch =
                        new Meal(
                                today,
                                MealType.LUNCH
                        );

                if (rice != null) {
                    lunch.getMenuItems().add(rice);
                }

                if (chicken != null) {
                    lunch.getMenuItems().add(chicken);
                }

                mealRepository.save(lunch);


                // Dinner

                Meal dinner =
                        new Meal(
                                today,
                                MealType.DINNER
                        );

                if (chapati != null) {
                    dinner.getMenuItems().add(chapati);
                }

                mealRepository.save(dinner);
            }
        };
    }
}