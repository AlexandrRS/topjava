package ru.javawebinar.topjava.web;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.service.UserService;

/**
 * Created by alexandr on 26.10.15.
 */
public abstract class AbstractUserControllerTest extends AbstractControllerTest{
    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        userService.evictCache();
    }
}
