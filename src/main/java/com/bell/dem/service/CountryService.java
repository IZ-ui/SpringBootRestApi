package com.bell.dem.service;

import com.bell.dem.view.CountryView;

import java.util.List;

/**
 * Сервис для работы с Country
 */
public interface CountryService {

    /**
     * Получить список стран
     */
    List<CountryView> countries();
}
