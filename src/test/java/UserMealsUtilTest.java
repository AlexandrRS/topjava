import org.junit.Test;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for UserMealsUtil
 * Created by alexandr on 29.08.15.
 */
public class UserMealsUtilTest {
    @Test
    public void testGetFilteredMealsWithExceeded(){
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 501)
        );
        List<UserMealWithExceed> mealWithExceeds = UserMealsUtil.getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        assertEquals(2, mealWithExceeds.size());
        assertEquals(false, mealWithExceeds.get(0).isExceed());
        assertEquals(false, mealWithExceeds.get(1).isExceed());
        assertEquals(10, mealWithExceeds.get(0).getDateTime().getHour());
        assertEquals(10, mealWithExceeds.get(1).getDateTime().getHour());
        List<UserMealWithExceed> mealWithExceeds2 = UserMealsUtil.getFilteredMealsWithExceeded(mealList, LocalTime.of(13, 0), LocalTime.of(21,0), 1500);
        assertEquals(4, mealWithExceeds2.size());
        assertEquals(false, mealWithExceeds2.get(0).isExceed());
        assertEquals(false, mealWithExceeds2.get(1).isExceed());
        assertEquals(true, mealWithExceeds2.get(2).isExceed());
        assertEquals(true, mealWithExceeds2.get(3).isExceed());
        List<UserMealWithExceed> mealWithExceeds3 = UserMealsUtil.getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 1499);
        assertEquals(2, mealWithExceeds3.size());
        assertEquals(true, mealWithExceeds3.get(0).isExceed());
        assertEquals(true, mealWithExceeds3.get(1).isExceed());
    }
}
