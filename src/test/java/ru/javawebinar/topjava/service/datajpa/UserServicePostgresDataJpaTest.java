package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.BaseUserServiceTest;

import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.Profiles.POSTGRES;

/**
 * Created by alexandr on 13.10.15.
 */
@ActiveProfiles({POSTGRES, DATAJPA})
public class UserServicePostgresDataJpaTest extends BaseUserServiceTest {
}
