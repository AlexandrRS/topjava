package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.service.UserMealService;

/**
 * Created by alexandr on 26.10.15.
 */
public abstract class AbstractUserMealControllerTest extends AbstractControllerTest{

    @Autowired
    protected UserMealService service;
}
