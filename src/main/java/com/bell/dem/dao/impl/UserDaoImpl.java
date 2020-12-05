package com.bell.dem.dao.impl;

import com.bell.dem.dao.UserDao;
import com.bell.dem.model.Document;
import com.bell.dem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> allByFilter(User user) {
        String firstName = user.getFirstName();
        String secondName = user.getSecondName();
        String middleName = user.getMiddleName();
        String position = user.getPosition();
        Integer officeId = user.getOffice().getId();
        //
        Document document = user.getDocument();
        Integer docCode = null;
        if (document != null) {
            docCode = document.getDocType().getCode();
        }
        Integer citizenshipCode = null;
        if (user.getCountry() != null) {
            citizenshipCode = user.getCountry().getCode();
        }
        //
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<User> root = criteria.from(User.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("office").get("id"), officeId));
        if (firstName != null) {
            predicates.add(builder.equal(root.get("firstName"), firstName));
        }
        if (secondName != null && root.get("secondName") != null) {
            predicates.add(builder.equal(root.get("secondName"), secondName));
        }
        if (middleName != null) {
            predicates.add(builder.equal(root.get("middleName"), middleName));
        }
        if (position != null) {
            predicates.add(builder.equal(root.get("position"), position));
        }
        //
        if (docCode != null) {
            predicates.add(builder.equal(root.get("document").get("docType").get("code"), docCode));
        }
        if (citizenshipCode != null) {
            predicates.add(builder.equal(root.get("country").get("code"), citizenshipCode));
        }
        //
        criteria.select(root)
                .where(predicates.toArray(new Predicate[]{}));

        TypedQuery<User> query = em.createQuery(criteria);
        return query.getResultList();


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User loadById(Integer id) {
        return em.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user) {
        em.merge(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        em.persist(user);
    }
}
