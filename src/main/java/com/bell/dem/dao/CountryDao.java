package com.bell.dem.dao;

import com.bell.dem.model.Country;

import java.util.List;

/**
 * DAO для работы с Country
 */
public interface CountryDao {

    /**
     * Получить все объекты Country
     */
    List<Country> all();


    /**
     * Получить Country по code
     */
    Country loadByCode(Integer code);
}
