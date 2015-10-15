package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.BaseUserMealServiceTest;

import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.Profiles.HSQLDB;

/**
 * Created by alexandr on 13.10.15.
 */
@ActiveProfiles({HSQLDB, DATAJPA})
public class UserMealServiceHsqldbDataJpaTest extends BaseUserMealServiceTest {

}
