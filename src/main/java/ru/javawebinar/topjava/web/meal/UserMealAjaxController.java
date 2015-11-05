package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by alexandr on 01.11.15.
 */
@RestController
@RequestMapping("/ajax/meals")
public class UserMealAjaxController extends AbstractUserMealController {

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

   /* @Override
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }*/

    @Override
    @RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getBetween(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("startTime") LocalTime startTime,
            @RequestParam("endDate") LocalDate endDate,
            @RequestParam("endTime") LocalTime endTime
    ) {
        return super.getBetween(startDate == null? TimeUtil.MIN_DATE : startDate,
                startTime == null? LocalTime.MIN : startTime,
                endDate == null? TimeUtil.MAX_DATE : endDate,
                endTime == null? LocalTime.MAX : endTime);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateOrCreate (
            @RequestParam("id") Integer id,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
            @RequestParam("description") String description,
            @RequestParam("calories") Integer calories
    ) {
        UserMeal userMeal = new UserMeal(dateTime, description, calories);
        if (id == 0) {
            super.create(userMeal);
        } else {
            super.update(userMeal, id);
        }
    }
}

