package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by alexandr on 12.10.15.
 */
@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository <UserMeal, Integer> {
    @Transactional
    UserMeal save(UserMeal userMeal);
    @Transactional
    @Query(name = UserMeal.GET)
    UserMeal get(@Param("id") int id, @Param("userId")int userId);
    @Query(name = UserMeal.ALL_SORTED)
    List<UserMeal> findAllByUserId(@Param("userId")int userId);
    @Query(name = UserMeal.GET_BETWEEN)
    List<UserMeal> findAllByUserIdBetween(@Param("startDate") LocalDateTime startDate,
                                          @Param("endDate")LocalDateTime endDate,
                                          @Param("userId")int userId);
    @Query("SELECT um FROM UserMeal um JOIN FETCH um.user user WHERE um.id = :id AND user.id = :user_id")
    UserMeal getWithUser(@Param("id")int id, @Param("user_id")int userId);
}
