package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * Created by alexandr on 15.09.15.
 */
public interface MealsRepository {
    List<UserMeal> getAllMeals();
}
