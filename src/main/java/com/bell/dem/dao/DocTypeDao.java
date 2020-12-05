package com.bell.dem.dao;

import com.bell.dem.model.DocType;

import java.util.List;

/**
 * DAO для работы с DocType
 */
public interface DocTypeDao {

    /**
     * Получить все объекты DocType
     */
    List<DocType> all();


    /**
     * Получить DocType по code
     */
    DocType loadByCode(Integer code);
}
