package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository{
    @Autowired
    private ProxyUserMealRepository proxyUserMeal;

    @Autowired
    private ProxyUserRepository proxyUser;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        User ref = proxyUser.findOne(userId);
        userMeal.setUser(ref);
        if(userMeal.isNew()) {
            return proxyUserMeal.save(userMeal);
        } else {
            return proxyUserMeal.get(userMeal.getId(), userId) == null ? null : proxyUserMeal.save(userMeal);
        }
    }
    @Override
    public boolean delete(int id, int userId) {
        UserMeal userMeal = proxyUserMeal.get(id, userId);
        if (userMeal == null) return false;
        proxyUserMeal.delete(userMeal);
        return true;
    }
    @Override
    public UserMeal get(int id, int userId) {
        return proxyUserMeal.get(id, userId);
    }
    @Override
    public List<UserMeal> getAll(int userId) {
        return proxyUserMeal.findAllByUserId(userId);
    }
    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxyUserMeal.findAllByUserIdBetween(startDate, endDate, userId);
    }
    @Override
    public UserMeal getWithUser(int id, int userId) {
        return proxyUserMeal.getWithUser(id, userId);
    }
}
