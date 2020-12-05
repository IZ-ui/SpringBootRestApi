package com.bell.dem.service.impl;

import com.bell.dem.dao.OfficeDao;
import com.bell.dem.dao.OrganizationDao;
import com.bell.dem.exception.IncorrectInputParameterException;
import com.bell.dem.exception.NotFoundEntityException;
import com.bell.dem.model.Office;
import com.bell.dem.model.Organization;
import com.bell.dem.service.OfficeService;
import com.bell.dem.view.OfficeInView;
import com.bell.dem.view.OrgOffShortView;
import com.bell.dem.view.OfficeOutView;
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
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final OrganizationDao organizationDao;
    private final MapperFactory mapperFactory;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OrganizationDao organizationDao, MapperFactory mapperFactory) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrgOffShortView> getByFilter(OfficeInView officeInView) {
        Integer orgId = officeInView.getOrgId();
        if (Objects.isNull(orgId)) {
            throw new IncorrectInputParameterException("orgId");
        }
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        Office office = mapperFacade.map(officeInView, Office.class);
        Organization organization = organizationDao.loadById(orgId);
        office.setOrganization(organization);

        try {
            List<Office> listOffices = officeDao.allByFilter(office);
            if (listOffices.isEmpty()) {
                throw new NotFoundEntityException("Office");
            }
            return mapperFacade.mapAsList(listOffices, OrgOffShortView.class);
        } catch (Exception e) {
            throw new NotFoundEntityException("2nd Office");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeOutView getByID(int id) {
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        Office office = officeDao.loadById(id);
        if (office == null) {
            throw new NotFoundEntityException("Office", id);
        }
        return mapperFacade.map(office, OfficeOutView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeInView officeInView) {
        if (Objects.isNull(officeInView.getId())) {
            throw new IncorrectInputParameterException("id");
        }
        checkParams(officeInView);
        try {
            Office office = mapperFactory.getMapperFacade().map(officeInView, Office.class);
            officeDao.update(office);
        } catch (Exception e) {
            throw new NotFoundEntityException("Office");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OfficeInView officeInView) {
        Integer orgId = officeInView.getOrgId();
        if (Objects.isNull(orgId)) {
            throw new IncorrectInputParameterException("OrgId");
        }
        Organization organization = organizationDao.loadById(orgId);
        if (organization == null) {
            throw new NotFoundEntityException("Office's organization");
        }
        Office officeNew = mapperFactory.getMapperFacade().map(officeInView, Office.class);
        officeNew.setOrganization(organization);
        officeDao.save(officeNew);
    }

    private void checkParams(OfficeInView officeInView) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", officeInView.getName());
        params.put("address", officeInView.getAddress());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (Objects.isNull(entry.getValue())) {
                throw new IncorrectInputParameterException(entry.getKey());
            }
        }
    }
}
