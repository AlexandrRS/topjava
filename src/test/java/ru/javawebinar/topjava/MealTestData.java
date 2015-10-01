package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final int USER_ID = 100000;
    public static final int ADMIN_ID = 100001;
    public static final int BREAKFAST_ID = 100002;
    public static final int USER_DINNER_ID = 100003;

    public static final UserMeal USER_DINNER = new UserMeal(100003, LocalDateTime.parse("2015-05-30T13:00"), "Обед", 1000);

    public static final Collection USER_MEAL = Arrays.asList(
            new UserMeal(100003, LocalDateTime.parse("2015-05-30T13:00"), "Обед", 1000),
            new UserMeal(100002, LocalDateTime.parse("2015-05-30T10:00"), "Завтрак", 500));
    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
