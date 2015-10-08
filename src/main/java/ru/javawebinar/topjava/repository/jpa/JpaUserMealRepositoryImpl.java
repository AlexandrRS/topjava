package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        User user = em.getReference(User.class, userId);
        userMeal.setUser(user);
        if (userMeal.isNew()) {
            em.persist(userMeal);
        } else {
            int changes = em.createQuery("UPDATE UserMeal SET dateTime = :date, description = :description, calories = :calories WHERE id = :id AND user = :user")
                    .setParameter("date", userMeal.getDateTime())
                    .setParameter("description", userMeal.getDescription())
                    .setParameter("calories", userMeal.getCalories())
                    .setParameter("id", userMeal.getId())
                    .setParameter("user", user)
                    .executeUpdate();
            if (changes == 0) return null;
        }
        return userMeal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        User user = em.getReference(User.class, userId);
        return em.createQuery("DELETE FROM UserMeal um WHERE um.id = :id AND um.user =:user")
                .setParameter("id", id)
                .setParameter("user", user)
                .executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        User user = em.getReference(User.class, userId);
        UserMeal userMeal;
        try {
            userMeal = em.createQuery("SELECT um from UserMeal um WHERE um.id = :id AND um.user = :user", UserMeal.class)
                    .setParameter("id", id)
                    .setParameter("user", user)
                    .getSingleResult();
        } catch (NoResultException e) {
            userMeal = null;
        }
        return userMeal;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        User user = em.getReference(User.class, userId);
        return em.createQuery("SELECT um from UserMeal um WHERE um.user = :user ORDER BY um.dateTime DESC")
                .setParameter("user", user)
                .getResultList();

    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        User user = em.getReference(User.class, userId);
        return em.createQuery("SELECT um from UserMeal um WHERE um.user = :user AND um.dateTime >= :start AND um.dateTime <= :end ORDER BY um.dateTime DESC")
                .setParameter("start", startDate)
                .setParameter("end", endDate)
                .setParameter("user", user)
                .getResultList();
    }
}