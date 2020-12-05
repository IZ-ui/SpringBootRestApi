package com.bell.dem.service.impl;

import com.bell.dem.dao.UserDao;
import com.bell.dem.dao.OfficeDao;
import com.bell.dem.dao.CountryDao;
import com.bell.dem.dao.DocTypeDao;
import com.bell.dem.exception.IncorrectInputParameterException;
import com.bell.dem.exception.NotFoundEntityException;
import com.bell.dem.model.Office;
import com.bell.dem.model.User;
import com.bell.dem.model.Document;
import com.bell.dem.model.DocType;
import com.bell.dem.model.Country;
import com.bell.dem.service.UserService;
import com.bell.dem.view.UserInView;
import com.bell.dem.view.UserOutView;
import com.bell.dem.view.UserShortOutView;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {

    private final MapperFactory mapperFactory;
    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final CountryDao countryDao;
    private final DocTypeDao docTypeDao;

    public UserServiceImpl(MapperFactory mapperFactory, UserDao userDao, OfficeDao officeDao, CountryDao countryDao, DocTypeDao docTypeDao) {
        this.mapperFactory = mapperFactory;
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.countryDao = countryDao;
        this.docTypeDao = docTypeDao;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserShortOutView> getByFilter(UserInView userInView) {
        Integer officeId = userInView.getOfficeId();
        if (Objects.isNull(officeId)) {
            throw new IncorrectInputParameterException("officeId");
        }

        mapperFactory
                .classMap(UserInView.class, User.class)
                .field("docCode", "document.docType.code")
                .field("citizenshipCode", "country.code")
                .byDefault()
                .register();

        User user = mapperFactory.getMapperFacade().map(userInView, User.class);
        Office office = officeDao.loadById(officeId);
        if (office == null) {
            throw new IncorrectInputParameterException("officeId", "User");
        }
        user.setOffice(office);

        List<User> users = userDao.allByFilter(user);
        if (users.isEmpty()) {
            throw new NotFoundEntityException("User");
        }
//        mapperFactory
//                .classMap(User.class, UserOutView.class)
//                .field("document.docType.code","docName")
//                .field("country.code","citizenshipCode")
//                .byDefault()
//                .register();
        return users.stream()
                .map(mapperFactory.getMapperFacade(User.class, UserShortOutView.class)::map)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserOutView getByID(int id) {
        User user = userDao.loadById(id);
        if (user == null) {
            throw new NotFoundEntityException("User", id);
        }
        mapperFactory
                .classMap(User.class, UserOutView.class)
                .field("document.docType.name", "docName")
                .field("document.docNumber", "docNumber")
                .field("document.docDate", "docDate")
                .field("country.name", "citizenshipName")
                .field("country.code", "citizenshipCode")
                .byDefault()
                .register();

        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        return mapperFacade.map(user, UserOutView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(UserInView userInView) {
        if (Objects.isNull(userInView.getId())) {
            throw new IncorrectInputParameterException("id");
        }
        checkParams(userInView);

        mapperFactory
                .classMap(UserInView.class, User.class)
                .field("docDate", "document.docDate")
                .field("docNumber", "document.docNumber")
                .field("docName", "document.docType.name")
                .field("citizenshipCode", "country.code")
                .byDefault()
                .register();
        User newUser = mapperFactory.getMapperFacade().map(userInView, User.class);
        Integer id = newUser.getId();
        User updatedUser = userDao.loadById(id);
        if (updatedUser == null) {
            throw new NotFoundEntityException("User", id);
        }
        if (userInView.getOfficeId() != null) {
            Office office = officeDao.loadById(userInView.getOfficeId());
            updatedUser.setOffice(office);
        }
        updatedUser.setFirstName(newUser.getFirstName());
        if (newUser.getSecondName() != null) {
            updatedUser.setSecondName(newUser.getSecondName());
        }
        if (newUser.getMiddleName() != null) {
            updatedUser.setMiddleName(newUser.getMiddleName());
        }
        updatedUser.setPosition(newUser.getPosition());
        if (newUser.getPhone() != null) {
            updatedUser.setPhone(newUser.getPhone());
        }
        if (newUser.getDocument() != null) {
            Document document = updatedUser.getDocument();
            if (userInView.getDocCode() != null) {
                DocType docType = docTypeDao.loadByCode(userInView.getDocCode());
                if (docType == null) {
                    throw new NotFoundEntityException("docCode");
                }
                document.setDocType(docType);
                document.setDocNumber(userInView.getDocNumber());
                document.setDocDate(userInView.getDocDate());
            }
            document.setUser(updatedUser);
            updatedUser.setDocument(document);
        }
        if (newUser.getCountry() != null && newUser.getCountry().getCode() != null) {
            Country country = countryDao.loadByCode(newUser.getCountry().getCode());
            if (country == null) {
                throw new NotFoundEntityException("citizenshipCode");
            }
            updatedUser.setCountry(country);
        }
        try {
            userDao.update(updatedUser);
        } catch (Exception e) {
            throw new IncorrectInputParameterException("input", "User");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(UserInView userInView) {
        Integer officeId = userInView.getOfficeId();
        if (Objects.isNull(officeId)) {
            throw new IncorrectInputParameterException("officeId");
        }
        checkParams(userInView);

        mapperFactory
                .classMap(UserInView.class, User.class)
                .field("docDate", "document.docDate")
                .field("docNumber", "document.docNumber")
                .field("docCode", "document.docType.code")
                .field("docName", "document.docType.name")
                .field("citizenshipCode", "country.code")
                .byDefault()
                .register();
        User newUser = mapperFactory.getMapperFacade().map(userInView, User.class);
        Office office = officeDao.loadById(officeId);
        if (office == null) {
            throw new NotFoundEntityException("User's office");
        }
        newUser.setOffice(office);
        if (userInView.getCitizenshipCode() != null) {
            Country country = countryDao.loadByCode(userInView.getCitizenshipCode());
            if (country == null) {
                throw new NotFoundEntityException("User's country");
            }
            newUser.setCountry(country);
        }
        Document newUserDocument = newUser.getDocument();
        if (newUserDocument != null) {
            newUserDocument.setUser(newUser);
            DocType docType = new DocType();
            if (userInView.getDocCode() != null) {
                docType = docTypeDao.loadByCode(userInView.getDocCode());
                newUserDocument.setDocType(docType);
            }
            if (docType == null) {
                throw new NotFoundEntityException("Users document code");
            }
            newUser.setDocument(newUserDocument);
        }
        userDao.save(newUser);
    }

    private void checkParams(UserInView userInView) {
        Map<String, Object> params = new HashMap<>();
        params.put("firstName", userInView.getFirstName());
        params.put("position", userInView.getPosition());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (Objects.isNull(entry.getValue())) {
                throw new IncorrectInputParameterException(entry.getKey());
            }
        }
    }
}
