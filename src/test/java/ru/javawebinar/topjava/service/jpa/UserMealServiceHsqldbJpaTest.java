package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.BaseUserMealServiceTest;

import static ru.javawebinar.topjava.Profiles.HSQLDB;
import static ru.javawebinar.topjava.Profiles.JPA;

/**
 * Created by alexandr on 13.10.15.
 */
@ActiveProfiles({HSQLDB, JPA})
public class UserMealServiceHsqldbJpaTest extends BaseUserMealServiceTest {
    
}
