package com.bell.dem.service.impl;

import com.bell.dem.dao.DocTypeDao;
import com.bell.dem.model.DocType;
import com.bell.dem.service.DocTypeService;
import com.bell.dem.view.DocTypeView;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class DocTypeServiceImpl implements DocTypeService {

    private final DocTypeDao dao;
    private final MapperFactory mapperFactory;

    @Autowired
    public DocTypeServiceImpl(DocTypeDao dao, MapperFactory mapperFactory) {
        this.dao = dao;
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocTypeView> docs() {
        List<DocType> docTypes = dao.all();
        return docTypes.stream()
                .map(mapperFactory.getMapperFacade(DocType.class, DocTypeView.class)::map)
                .collect(Collectors.toList());
    }
}
