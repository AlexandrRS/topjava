package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.MealsFilter;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    UserMeal save(Integer loggedUserId, UserMeal userMeal);

    void delete(Integer loggedUserId, int id) throws NotFoundException;

    UserMeal get(Integer loggedUserId, int id) throws NotFoundException;

   /* List<UserMeal> getAll(Integer loggedUserId);*/

    List<UserMealWithExceed> getFilteredData(Integer loggedUserId, LocalDate dateFrom, LocalDate dateTo, LocalTime timeFrom, LocalTime timeTo, int calories);
}
