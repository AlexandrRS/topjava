package ru.javawebinar.topjava.web.usermeal;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.web.AbstractUserMealControllerTest;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * Created by alexandr on 26.10.15.
 */
public class UserMealControllerTest extends AbstractUserMealControllerTest {

    @Test
    public void testCreateUserMeal() throws Exception {

        UserMeal created = new UserMeal(LocalDateTime.parse("2015-10-27T10:15:30"), "Ужин", 1000);
        mockMvc.perform(post("/meals", model().attribute("meal", new UserMeal()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("dateTime", "2015-10-27T10:15:30")
                .param("description", "Ужин")
                .param("calories", "1000")                        )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:meals"))
                .andExpect(redirectedUrl("meals"));
        created.setId(100010);
        UserMeal returned = service.get(100010, 100000);
        MATCHER.assertEquals(created, returned);
    }

    @Test
    public void testUserMeal() throws Exception {
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealList.jsp"))
                .andExpect(model().attribute("mealList", hasSize(6)))
                .andExpect(model().attribute("mealList", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ + 5)),
                                hasProperty("description", is(MEAL1.getDescription()))
                        )
                )));
    }



    @Test
    public void testUseFilter() throws Exception {

        mockMvc.perform(post("/meals/filter")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("startDate", "2015-05-30")
                .param("endDate", "2015-05-30")
                .param("startTime", "09:00")
                .param("endTime", "11:00")                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealList.jsp"))
                .andExpect(model().attribute("mealList", hasSize(1)))
                .andExpect(model().attribute("mealList", hasItem(
                        allOf(
                                hasProperty("id", is(MEAL1_ID)),
                                hasProperty("description", is(MEAL1.getDescription()))
                        )
                )));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(get("/meals/delete")
                .param("id", String.valueOf(MEAL1_ID)))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/meals"));
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealList.jsp"))
                .andExpect(model().attribute("mealList", hasSize(5)));
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(get("/meals/update")
                .param("id", String.valueOf(MEAL1_ID)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealEdit"))
                .andExpect(model().attribute("meal", MEAL1)
                );
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(get("/meals/create")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealEdit"))
                .andExpect(model().attribute("meal", allOf(
                        hasProperty("id", is(nullValue())),
                        hasProperty("description", is("")),
                        hasProperty("calories", is(1000)),
                        hasProperty("dateTime", not(""))
                )));
    }
}
