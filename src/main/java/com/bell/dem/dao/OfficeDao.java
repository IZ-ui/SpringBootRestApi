package com.bell.dem.dao;

import com.bell.dem.model.Office;

import java.util.List;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {

    /**
     * Получить все объекты Office по фильтру
     */
    List<Office> allByFilter(Office office);

    /**
     * Получить Office по идентификатору
     */
    Office loadById(Integer id);

    /**
     * Обновить Office
     */
    void update(Office office);

    /**
     * Сохранить Office
     */
    void save(Office office);
}
