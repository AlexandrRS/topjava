package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.MealsFilter;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepository implements UserMealRepository {
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(1, new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        save(1, new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        save(1, new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        save(1, new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        save(1, new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        save(1, new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        save(2, new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "breakfast", 500));
        save(2, new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "lunch", 1000));
        save(2, new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "dinner", 500));
        save(2, new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "breakfast", 1000));
        save(2, new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "lunch", 500));
        save(2, new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "dinner", 510));

    }

    @Override
    public UserMeal save(Integer loggedUserId, UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        } else {
            if (repository.get(userMeal.getId()).getOwnerId() != loggedUserId) {
                throw new NotFoundException(String.format("Пытаетесь редактировать чужое. Ваш Id: %d, Еда принадлежит: %d", loggedUserId, userMeal.getOwnerId()));
            }
        }
        userMeal.setOwnerId(loggedUserId);
        return repository.put(userMeal.getId(), userMeal);
    }

    @Override
    public void delete(Integer loggedUserId, int id) {
        if (repository.get(id).getOwnerId() != loggedUserId) {
            throw new NotFoundException("Пытаетесь удалить чужое!");
        }
        repository.remove(id);
    }

    @Override
    public UserMeal get(Integer loggedUserId, int id) {
        if (repository.get(id).getOwnerId() != loggedUserId) {
            throw new NotFoundException("Пытаетесь получить чужое!");
        }
        return repository.get(id);
    }

    /*@Override
    public Collection<UserMeal> getAll() {
        return repository.values();
    }*/

    @Override
    public List<UserMeal> getFilteredData(Integer loggedUserId, LocalDate dateFrom, LocalDate dateTo) {
        return repository.values()
                .stream()
                .filter(um -> um.getOwnerId() == loggedUserId &&
                        um.getDateTime().toLocalDate().compareTo(dateFrom) >= 0 &&
                        um.getDateTime().toLocalDate().compareTo(dateTo) <= 0)
                .collect(Collectors.toList());
    }
}

