package com.bell.dem.service.impl;

import com.bell.dem.dao.CountryDao;
import com.bell.dem.model.Country;
import com.bell.dem.service.CountryService;
import com.bell.dem.view.CountryView;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao dao;
    private final MapperFactory mapperFactory;

    @Autowired
    public CountryServiceImpl(CountryDao dao, MapperFactory mapperFactory) {
        this.dao = dao;
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CountryView> countries() {
        List<Country> countries = dao.all();
        return countries.stream()
                .map(mapperFactory.getMapperFacade(Country.class, CountryView.class)::map)
                .collect(Collectors.toList());
    }
}
