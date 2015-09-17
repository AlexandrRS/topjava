package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.util.List;

/**
 * Created by alexandr on 15.09.15.
 */
public interface UserMealService {
    List<UserMealWithExceed> getAllUserMealsWithExceed();
    UserMeal addUserMeal(UserMeal userMeal);
    UserMeal deleteUserMealById(int id);
    UserMeal getUserMealById(int id);
    UserMeal updateUserMeal(UserMeal userMeal);
}
