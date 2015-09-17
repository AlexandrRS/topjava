package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.mock.DataSource;

import java.util.List;

public class MealsRepositoryImpl implements MealsRepository{
    private DataSource dataSource = DataSource.getInstance();

    @Override
    //ToDo refactor to clone
    public List<UserMeal> getAllMeals() {
        return dataSource.getUserMeals();
    }
}