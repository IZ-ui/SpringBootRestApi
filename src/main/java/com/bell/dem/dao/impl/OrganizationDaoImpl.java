package com.bell.dem.dao.impl;

import com.bell.dem.dao.OrganizationDao;
import com.bell.dem.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> allByFilter(Organization organization) {
        String inn = organization.getInn();
        Boolean isActive = organization.getIsActive();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        Root<Organization> root = criteria.from(Organization.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("name"), organization.getName()));
        if (inn != null) {
            predicates.add(builder.equal(root.get("inn"), inn));
        }
        if (isActive != null) {
            predicates.add(builder.equal(root.get("isActive"), isActive));
        }
        criteria.select(root).where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Organization> query = em.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(Integer id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization organization) {
        int id = organization.getId();
        Organization updated = loadById(id);
        updated.setName(organization.getName());
        updated.setFullName(organization.getFullName());
        updated.setInn(organization.getInn());
        updated.setKpp(organization.getKpp());
        updated.setAddress(organization.getAddress());
        if (organization.getPhone() != null) {
            updated.setPhone(organization.getPhone());
        }
        updated.setIsActive(organization.getIsActive());
        em.merge(updated);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }
}
