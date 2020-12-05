package com.bell.dem.service;

import com.bell.dem.view.OfficeInView;
import com.bell.dem.view.OrgOffShortView;
import com.bell.dem.view.OfficeOutView;

import java.util.List;

/**
 * Сервис для работы с Office
 */
public interface OfficeService {
    /**
     * Получить Office по входным параметрам
     */
    List<OrgOffShortView> getByFilter(OfficeInView officeInView);

    /**
     * Получить Office по id
     */
    OfficeOutView getByID(int id);

    /**
     * Обновить Office
     */
    void update(OfficeInView officeInView);

    /**
     * Сохранить Office
     */
    void save(OfficeInView officeInView);
}
