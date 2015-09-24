package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {
    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(Integer loggedUserId, UserMeal userMeal) {
        return repository.save(loggedUserId, userMeal);
    }

    @Override
    public void delete(Integer loggedUserId, int id) throws NotFoundException {
        repository.delete(loggedUserId, id);
    }

    @Override
    public UserMeal get(Integer loggedUserId, int id) throws NotFoundException {
        return repository.get(loggedUserId, id);
    }

    /*@Override
    public List<UserMeal> getAll(Integer loggedUserId) {
        return repository.getAll()
                .stream()
                .filter(um -> um.getOwnerId() == loggedUserId)
                .collect(Collectors.toList());
    }*/

    @Override
    public List<UserMealWithExceed> getFilteredData(Integer loggedUserId, LocalDate dateFrom, LocalDate dateTo, LocalTime timeFrom, LocalTime timeTo, int calories) {
        return UserMealsUtil.getFilteredWithExceeded(repository.getFilteredData(loggedUserId, dateFrom, dateTo), timeFrom, timeTo, calories);
    }
}
