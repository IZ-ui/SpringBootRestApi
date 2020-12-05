package com.bell.dem.dao;

import com.bell.dem.model.User;

import java.util.List;

/**
 * DAO для работы с User
 */
public interface UserDao {

    /**
     * Получить все объекты Office по фильтру
     */
    List<User> allByFilter(User user);

    /**
     * Получить User по идентификатору
     */
    User loadById(Integer id);

    /**
     * Обновить User
     */
    void update(User user);

    /**
     * Сохранить User
     */
    void save(User user);
}
