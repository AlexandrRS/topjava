package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@Controller
public class RootController {

    private static final LoggerWrapper LOG = LoggerWrapper.get(MealServlet.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserMealRestController userMealService;
    @Autowired
    private UserMealRestController mealController;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "userList";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        LoggedUser.setId(userId);
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") Integer id, Model model) {
        LOG.info("Delete {}", id);
        mealController.delete(id);
        return "redirect:/meals";
    }
    @RequestMapping(value = "/meals/create", method = RequestMethod.GET)
    public String create(Model model) {
        final UserMeal meal = new UserMeal(LocalDateTime.now(), "", 1000);
        model.addAttribute("meal", meal);
        return "forward:/meals/addnew";
    }
    @RequestMapping(value = "/meals/update", method = RequestMethod.GET)
    public String create(@RequestParam(value = "id") Integer id, Model model) {
        final UserMeal meal = mealController.get(id);
        model.addAttribute("meal", meal);
        return "forward:/meals/addnew";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(Model model) {
        LOG.info("getAll");
        model.addAttribute("mealList", mealController.getAll());
        return "mealList";
    }

    @RequestMapping(value = "/meals/addnew", method = RequestMethod.GET)
    public String addMeal(Model model) {
        return "mealEdit";
    }
    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String mealList(@RequestParam(value="action", required = false) String action,
                           @RequestParam(value = "id", required = false) Integer id,
                           @RequestParam(value = "dateTime", required = false) String dateTime,
                           @RequestParam(value = "description", required = false) String description,
                           @RequestParam(value = "calories", required = false) Integer calories,
                           @RequestParam(value = "startDate", required = false) String startDate,
                           @RequestParam(value = "endDate", required = false) String endDate,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           Model model) {
        String result = "redirect:meals";
        if (action == null) {
            try {
                description = new String(description.getBytes("ISO-8859-1"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            UserMeal userMeal = new UserMeal(id, LocalDateTime.parse(dateTime), description, calories);
            LOG.info(userMeal.isNew() ? "Create {}" : "Update {}", userMeal);
            mealController.create(userMeal);
            model.addAttribute("mealList", userMealService.getAll());
        } else {
            model.addAttribute("mealList", mealController.getBetween(
                    TimeUtil.parseLocalDate(startDate, TimeUtil.MIN_DATE),
                    TimeUtil.parseLocalTime(startTime, LocalTime.MIN),
                    TimeUtil.parseLocalDate(endDate, TimeUtil.MAX_DATE),
                    TimeUtil.parseLocalTime(endTime, LocalTime.MAX)));
            result = "mealList";
        }
        return result;
    }
}