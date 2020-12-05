package com.bell.dem.service;

import com.bell.dem.view.OfficeOutView;
import com.bell.dem.view.OrganizationView;

import java.util.List;

/**
 * Сервис для работы с Organization
 */
public interface OrganizationService {

    /**
     * Получить Organization по входным параметрам
     */
    List<OfficeOutView> getByFilter(OrganizationView organization);

    /**
     * Получить Organization по id
     */
    OrganizationView getById(int id);

    /**
     * Обновить Organization
     */
    void update(OrganizationView organization);

    /**
     * Сохранить Organization
     */
    void save(OrganizationView organization);
}
