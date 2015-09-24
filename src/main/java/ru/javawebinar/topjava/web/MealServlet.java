package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.MealsFilter;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
@Component
public class MealServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealServlet.class);

    private MealsFilter mealsFilter;

    @Autowired
    private UserMealRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mealsFilter = new MealsFilter();
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            appCtx.getAutowireCapableBeanFactory().autowireBean(this);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("doFilter")) {
                LOG.info("doFilter");
                LocalDate dateFrom = LocalDate.MIN;
                LocalDate dateTo = LocalDate.MAX;
                LocalTime timeFrom = LocalTime.MIN;
                LocalTime timeTo = LocalTime.MAX;

                try {
                    dateFrom = LocalDate.parse(request.getParameter("dateFrom"));
                } catch (Exception ignore) {
                    LOG.debug("error parsing dateFrom, set MIN");
                }
                try {
                    dateTo = LocalDate.parse(request.getParameter("dateTo"));
                } catch (Exception e) {
                    LOG.debug("error parsing dateTo, set MAX");
                }
                try {
                    timeFrom = LocalTime.parse(request.getParameter("timeFrom"));
                } catch (Exception e) {
                    LOG.debug("error parsing timeFrom, set MIN");
                }
                try {
                    timeTo = LocalTime.parse(request.getParameter("timeTo"));
                } catch (Exception e) {
                    LOG.debug("error parsing timeTO, set MAX");
                }

                mealsFilter.setDateFrom(dateFrom);
                mealsFilter.setDateTo(dateTo);
                mealsFilter.setTimeFrom(timeFrom);
                mealsFilter.setTimeTo(timeTo);
            } else if (action.equals("setUserId")){
                LOG.info("setUserId");
                LoggedUser.setId(Integer.parseInt(request.getParameter("userId")));
                mealsFilter = new MealsFilter();
            } else if (action.equals("edit")) {
                    String id = request.getParameter("id");
                    UserMeal userMeal = new UserMeal(id.isEmpty() ? null : Integer.valueOf(id),
                            LocalDateTime.parse(request.getParameter("dateTime")),
                            request.getParameter("description"),
                            Integer.valueOf(request.getParameter("calories")));
                    LOG.info(userMeal.isNew() ? "Create {}" : "Update {}", userMeal);
                    controller.save(userMeal);
                }
        }
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("mealList", controller.getFilteredData(mealsFilter, 2000));
            request.setAttribute("filter", mealsFilter);
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            controller.delete(id);
            response.sendRedirect("meals");
        } else {
            final UserMeal meal = action.equals("create") ?
                    new UserMeal(LocalDateTime.now(), "", 1000) :
                    controller.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
