package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.service.UserMealServiceImpl;
import ru.javawebinar.topjava.util.MealsFilter;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    @Autowired
    private UserMealServiceImpl service;

    public UserMeal save(UserMeal userMeal) {
        return service.save(LoggedUser.id(), userMeal);
    }
    public void delete(int id) {
        service.delete(LoggedUser.id(), id);
    }
    public UserMeal get(int id) {
        return service.get(LoggedUser.id(), id);
    }
    /*public List<UserMeal> getAll() {
        return service.getAll(LoggedUser.id());
    }*/
    public List<UserMealWithExceed> getFilteredData(MealsFilter mealsFilter, int calories) {
        return service.getFilteredData(LoggedUser.id(), mealsFilter.getDateFrom(), mealsFilter.getDateTo(), mealsFilter.getTimeFrom(), mealsFilter.getTimeTo(), calories);
    }
}
