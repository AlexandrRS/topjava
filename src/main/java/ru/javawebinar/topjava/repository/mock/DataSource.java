package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexandr on 14.09.15.
 */
public class DataSource {
    private static DataSource instance = new DataSource();
    private List<UserMeal> userMeals = Arrays.asList(
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 500),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 500),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500)
    );
    private DataSource() {}
    public static DataSource getInstance(){
        return instance;
    }
    public List<UserMeal> getUserMeals() {
        return userMeals;
    }
    //ToDo realise addOrUpdateUserMeal method
    public UserMeal addOrUpdateUserMeal(UserMeal userMeal) {
        return null;
    }
    //ToDo realise addUserMeal method
    private UserMeal addUserMeal(UserMeal userMeal) {
        return null;
    }
    //ToDo realise Update method
    private UserMeal updateUserMeal(UserMeal userMeal) {
        return null;
    }
}
