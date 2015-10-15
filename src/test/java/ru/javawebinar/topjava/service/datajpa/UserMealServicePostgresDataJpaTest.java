package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.BaseUserMealServiceTest;

import static ru.javawebinar.topjava.Profiles.*;

/**
 * Created by alexandr on 13.10.15.
 */
@ActiveProfiles({POSTGRES, DATAJPA})
public class UserMealServicePostgresDataJpaTest extends BaseUserMealServiceTest {

}
