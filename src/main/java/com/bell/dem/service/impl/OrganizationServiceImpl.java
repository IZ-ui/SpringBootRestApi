package com.bell.dem.service.impl;

import com.bell.dem.dao.OrganizationDao;
import com.bell.dem.exception.IncorrectInputParameterException;
import com.bell.dem.exception.NotFoundEntityException;
import com.bell.dem.model.Organization;
import com.bell.dem.service.OrganizationService;
import com.bell.dem.view.OfficeOutView;
import com.bell.dem.view.OrganizationView;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao dao;
    private final MapperFactory mapperFactory;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao, MapperFactory mapperFactory) {
        this.dao = dao;
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeOutView> getByFilter(OrganizationView organizationView) {
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        try {
            List<Organization> listOrganizations = dao.allByFilter(
                    mapperFacade.map(organizationView, Organization.class));
            if (listOrganizations.isEmpty()) {
                throw new NotFoundEntityException("Organization");
            }
            return mapperFacade.mapAsList(listOrganizations, OfficeOutView.class);
        } catch (Exception e) {
            throw new NotFoundEntityException("Organization");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationView getById(int id) {
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        Organization organization = dao.loadById(id);
        if (organization == null) {
            throw new NotFoundEntityException("Organization", id);
        }
        return mapperFacade.map(organization, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationView organizationView) {
        if (Objects.isNull(organizationView.getId())) {
            throw new IncorrectInputParameterException("id");
        }
        checkParams(organizationView);
        try {
            Organization organization = mapperFactory.getMapperFacade().map(organizationView, Organization.class);
            dao.update(organization);
        } catch (Exception e) {
            throw new NotFoundEntityException("Organization");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OrganizationView organizationView) {
        checkParams(organizationView);
        try {
            Organization organization = mapperFactory.getMapperFacade().map(organizationView, Organization.class);
            dao.save(organization);
        } catch (Exception e) {
            throw new IncorrectInputParameterException("Organization", "name");
        }
    }

    private void checkParams(OrganizationView organizationView) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", organizationView.getName());
        params.put("fullName", organizationView.getFullName());
        params.put("inn", organizationView.getInn());
        params.put("kpp", organizationView.getKpp());
        params.put("address", organizationView.getAddress());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (Objects.isNull(entry.getValue())) {
                throw new IncorrectInputParameterException(entry.getKey());
            }
        }
    }
}
