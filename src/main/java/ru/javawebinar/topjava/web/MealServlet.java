package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserMealServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealServlet.class);
    private UserMealService userMealService = new UserMealServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");
        request.setAttribute("mealList", userMealService.getAllUserMealsWithExceed());
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("operation")) {
            case "del": LOG.debug("deleting meal to mealList");
                userMealService.deleteUserMealById(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("meals");
                break;
            case "add": {
                LOG.debug("adding meal to mealList");
                UserMeal userMeal = new UserMeal(
                        LocalDateTime.parse(request.getParameter("date")),
                        new String(request.getParameter("description").getBytes("ISO-8859-1")),
                        Integer.parseInt(request.getParameter("calories")),
                        0);
                userMealService.addUserMeal(userMeal);
                response.sendRedirect("meals");
                break;
            }
            case "edit": {
                LOG.debug("editing meal to mealList");
                request.setAttribute("userMeal", userMealService.getUserMealById(Integer.parseInt(request.getParameter("id"))));
                request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
                break;
            }
            case "update": {
                LOG.debug("updating meal to mealList");
                UserMeal userMeal = new UserMeal(
                        LocalDateTime.parse(request.getParameter("date")),
                        new String(request.getParameter("description").getBytes("ISO-8859-1")),
                        Integer.parseInt(request.getParameter("calories")),
                        Integer.parseInt(request.getParameter("id")));
                userMealService.updateUserMeal(userMeal);
                response.sendRedirect("meals");
            }
        }
    }
}
