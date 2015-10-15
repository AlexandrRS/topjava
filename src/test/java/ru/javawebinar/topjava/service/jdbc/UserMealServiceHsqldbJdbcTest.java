package ru.javawebinar.topjava.service.jdbc;

import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.BaseUserMealServiceTest;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.Profiles.HSQLDB;
import static ru.javawebinar.topjava.Profiles.JDBC;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

/**
 * Created by alexandr on 13.10.15.
 */
@ActiveProfiles({HSQLDB, JDBC})
public class UserMealServiceHsqldbJdbcTest extends BaseUserMealServiceTest {
    @Override
    public void testGetWithUser() throws Exception {

    }

    @Override
    public void testGetWithUserNotFound() throws Exception {

    }
    @Test
    public void testGet() throws Exception {
        UserMeal actual = service.get(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL, actual);
    }
}
