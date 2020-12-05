package com.bell.dem.service;

import com.bell.dem.view.DocTypeView;

import java.util.List;

/**
 * Сервис для работы с DocType
 */
public interface DocTypeService {

    /**
     * Получить список стран
     */
    List<DocTypeView> docs();
}
