package com.bell.dem.dao;

import com.bell.dem.model.Organization;

import java.util.List;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao {

    /**
     * Получить все объекты Organization по фильтру
     */
    List<Organization> allByFilter(Organization organization);

    /**
     * Получить Organization по идентификатору
     */
    Organization loadById(Integer id);

    /**
     * Обновить Organization
     */
    void update(Organization organization);

    /**
     * Сохранить Organization
     */
    void save(Organization organization);
}
