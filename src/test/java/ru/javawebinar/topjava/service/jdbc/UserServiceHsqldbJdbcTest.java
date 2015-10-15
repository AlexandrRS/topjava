package ru.javawebinar.topjava.service.jdbc;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.BaseUserServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.naming.OperationNotSupportedException;

import static ru.javawebinar.topjava.Profiles.HSQLDB;
import static ru.javawebinar.topjava.Profiles.JDBC;
import static ru.javawebinar.topjava.UserTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by alexandr on 13.10.15.
 */
@ActiveProfiles({HSQLDB, JDBC})
public class UserServiceHsqldbJdbcTest extends BaseUserServiceTest {
    @Override
    public void testGetWithMeals() throws Exception {
    }

    @Override
    @Test(expected = NotFoundException.class)
    public void testGetWitMealsNotFound() throws Exception {
        service.getWithMeals(1);
    }

    @Override
    @Test
    public void testGet() throws Exception {
        User user = service.get(USER_ID);
        MATCHER.assertEquals(USER, user);
    }
}
