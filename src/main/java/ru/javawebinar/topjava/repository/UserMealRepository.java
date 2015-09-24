package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.MealsFilter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(Integer loggedUserId, UserMeal userMeal);

    void delete(Integer loggedUserId, int id);

    UserMeal get(Integer loggedUserId, int id);

/*    Collection<UserMeal> getAll();*/

    List<UserMeal> getFilteredData(Integer loggedUserId, LocalDate dateFrom, LocalDate dateTo);
}
