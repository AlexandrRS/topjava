package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.BaseUserMealServiceTest;

import static ru.javawebinar.topjava.Profiles.JPA;
import static ru.javawebinar.topjava.Profiles.POSTGRES;

/**
 * Created by alexandr on 13.10.15.
 */
@ActiveProfiles({POSTGRES, JPA})
public class UserMealServicePostgresJpaTest extends BaseUserMealServiceTest {
    
}
