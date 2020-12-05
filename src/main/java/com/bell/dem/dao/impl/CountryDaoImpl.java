package com.bell.dem.dao.impl;

import com.bell.dem.dao.CountryDao;
import com.bell.dem.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class CountryDaoImpl implements CountryDao {

    private final EntityManager em;

    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> all() {
        CriteriaQuery<Country> criteria = em.getCriteriaBuilder().createQuery(Country.class);
        criteria.from(Country.class);
        return em.createQuery(criteria).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country loadByCode(Integer code) {
        return em.find(Country.class, code);
    }
}
