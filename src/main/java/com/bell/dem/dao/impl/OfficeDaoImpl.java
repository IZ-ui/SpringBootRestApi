package com.bell.dem.dao.impl;

import com.bell.dem.dao.OfficeDao;
import com.bell.dem.model.Office;
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
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> allByFilter(Office office) {
        String name = office.getName();
        String phone = office.getPhone();
        Boolean isActive = office.getIsActive();
        Organization organization = office.getOrganization();
        Integer orgId = 0;
        if (organization != null) {
            orgId = office.getOrganization().getId();
        }
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);
        Root<Office> root = criteria.from(Office.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("organization").get("id"), orgId));
        if (name != null) {
            predicates.add(builder.equal(root.get("name"), name));
        }
        if (phone != null) {
            predicates.add(builder.equal(root.get("phone"), phone));
        }
        if (isActive != null) {
            predicates.add(builder.equal(root.get("isActive"), isActive));
        }
        criteria.select(root).where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Office> query = em.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadById(Integer id) {
        return em.find(Office.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office) {
        int id = office.getId();
        Office updated = loadById(id);
        updated.setName(office.getName());
        updated.setAddress(office.getAddress());
        String phone = office.getPhone();
        if (phone != null) {
            updated.setPhone(phone);
        }
        Boolean isActive = office.getIsActive();
        if (isActive != null) {
            updated.setIsActive(isActive);
        }
        em.merge(updated);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        em.persist(office);
    }
}
