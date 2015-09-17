package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.repository.MealsRepository;
import ru.javawebinar.topjava.repository.MealsRepositoryImpl;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by alexandr on 15.09.15.
 */
public class UserMealServiceImpl implements UserMealService {
    private MealsRepository mealsRepository = new MealsRepositoryImpl();
    @Override
    public List<UserMealWithExceed> getAllUserMealsWithExceed() {
        return UserMealsUtil.getFilteredMealsWithExceeded(mealsRepository.getAllMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
    }
}
