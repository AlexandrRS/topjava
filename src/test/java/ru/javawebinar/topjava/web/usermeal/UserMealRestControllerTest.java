package ru.javawebinar.topjava.web.usermeal;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.AbstractUserMealControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by alexandr on 28.10.15.
 */
public class UserMealRestControllerTest extends AbstractUserMealControllerTest {

    public static final String REST_URL = UserMealRestController.REST_URL;

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(UserMealsUtil.getWithExceeded(service.getAll(LoggedUser.id), LoggedUser.getCaloriesPerDay())))
        );
    }

    @Test
    public void testGetBetween() throws Exception {
        LocalDateTime start = LocalDateTime.of(2015, 5, 31, 9, 0);
        LocalDateTime end = LocalDateTime.of(2015, 5, 31, 14, 0);

        TestUtil.print(mockMvc.perform(get(REST_URL + "/filter")
                        .param("startDateTime", start.toString())
                        .param("endDateTime", end.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(UserMealsUtil.getWithExceeded(service.getAll(LoggedUser.id), LoggedUser.getCaloriesPerDay()).stream().filter(u -> u.getDateTime().isAfter(start) && u.getDateTime().isBefore(end)).collect(Collectors.toList())))
        );
    }

    @Test
    public void testCreateWithLocation() throws Exception {
        UserMeal expected = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        UserMeal returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());
        MATCHER.assertEquals(expected, returned);
        List<UserMeal> expectedList = new ArrayList<>(USER_MEALS);
        expectedList.add(0, expected);
        MATCHER.assertCollectionEquals(expectedList, service.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal expected = getUpdated();
        mockMvc.perform(put(REST_URL + "/" + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(expected, service.get(MEAL1_ID, USER_ID));
    }
}