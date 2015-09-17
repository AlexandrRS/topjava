package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

/**
 * Created by alexandr on 14.09.15.
 */
public class DataSource {
    private static DataSource instance = new DataSource();
    private int maxUserMealId = 6;
    private List<UserMeal> userMeals = new ArrayList<>(Arrays.asList(
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500, 1),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 500, 2),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500, 3),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 500, 4),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 800, 5),
            new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 800, 6)
    ));
    private DataSource() {}
    public static DataSource getInstance(){
        return instance;
    }
    public List<UserMeal> getUserMeals() {
        return userMeals;
    }
    //ToDo realise addOrUpdateUserMeal method
        public UserMeal addOrUpdateUserMeal(UserMeal newUserMeal) {
        if (newUserMeal.getId() == 0) {
            return addUserMeal(newUserMeal);
        } else {
            return updateUserMeal(newUserMeal);
        }
    }
    //ToDo realise addUserMeal method
    private UserMeal addUserMeal(UserMeal newUserMeal) {
        UserMeal clone = newUserMeal.clone(++maxUserMealId);
        userMeals.add(clone);
        return clone;
    }
    //ToDo realise Update method
    private UserMeal updateUserMeal(UserMeal newUserMeal) {
        Iterator<UserMeal> iterator = userMeals.iterator();
        while (iterator.hasNext()){
            UserMeal userMeal = iterator.next();
            if (userMeal.getId() == newUserMeal.getId()) {
                iterator.remove();
                userMeals.add(newUserMeal);
                break;
            }
        }
        return null;
    }

    public UserMeal deleteUserMealById(int id) {
        Iterator<UserMeal> iterator = userMeals.iterator();
        while (iterator.hasNext()) {
            UserMeal userMeal = iterator.next();
            if (id == userMeal.getId()) {
                iterator.remove();
                return userMeal;
            }
        }
        return null;
    }

    public UserMeal getUserMealById(int id) {
        for (UserMeal userMeal : userMeals) {
            if (userMeal.getId() == id) {
                return userMeal;
            }
        }
        return null;
    }
}
