package com.bell.dem.service;

import com.bell.dem.view.UserShortOutView;
import com.bell.dem.view.UserOutView;
import com.bell.dem.view.UserInView;

import java.util.List;

/**
 * Сервис для работы с User
 */
public interface UserService {
    /**
     * Получить User по входным параметрам
     */
    List<UserShortOutView> getByFilter(UserInView userInView);

    /**
     * Получить User по id
     */
    UserOutView getByID(int id);

    /**
     * Обновить User
     */
    void update(UserInView userInView);

    /**
     * Сохранить User
     */
    void save(UserInView userInView);
}
