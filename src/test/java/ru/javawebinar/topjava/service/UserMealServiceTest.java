package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by alexandr on 30.09.15.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {
    @Autowired
    UserMealService service;

    @Autowired
    DbPopulator populator;

    @Before
    public void setUp() throws Exception {
        populator.execute();
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<UserMeal> all = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(USER_MEAL, all);
    }

    @Test
    public void testgetBetweenDateTimes() throws Exception {
        Collection<UserMeal> betweenDateTimes = service.getBetweenDateTimes(LocalDateTime.parse("2015-05-30T12:00"), LocalDateTime.parse("2015-05-30T14:00"), USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(USER_DINNER), betweenDateTimes);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(BREAKFAST_ID, USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(USER_DINNER), service.getAll(USER_ID));
    }

    @Test
    public void testSave() throws Exception {
        UserMeal userMeal = new UserMeal(LocalDateTime.parse("2015-05-30T18:00"), "Ужин", 1000);
        service.save(userMeal, ADMIN_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(userMeal), service.getAll(ADMIN_ID));
    }

    @Test
    public void testGet() throws Exception {
        UserMeal userMeal = service.get(USER_DINNER_ID, USER_ID);
        MATCHER.assertEquals(USER_DINNER, userMeal);
    }
    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(100002, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound2() throws Exception {
        service.get(100002, ADMIN_ID);
    }
    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = service.get(BREAKFAST_ID, USER_ID);
        updated.setCalories(333);
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(BREAKFAST_ID, USER_ID));
    }
    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        UserMeal userMeal = new UserMeal(
                USER_DINNER.getId(),
                USER_DINNER.getDateTime(),
                "Edited Dinner",
                333
        );
        service.update(userMeal, ADMIN_ID);
    }
}