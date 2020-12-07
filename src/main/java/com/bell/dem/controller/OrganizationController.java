package com.bell.dem.controller;

import com.bell.dem.service.OrganizationService;
import com.bell.dem.util.Validator;
import com.bell.dem.view.OrgOffShortView;
import com.bell.dem.view.OrganizationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller для Organization
 */
@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Метод для маппинга /list
     */
    @PostMapping("/list")
    public List<OrgOffShortView> getOrganizationByFilter(@Validated(OrganizationView.List.class)
                                                         @RequestBody OrganizationView organization,
                                                         BindingResult result) {
        Validator.validate(result);
        return organizationService.getByFilter(organization);
    }

    /**
     * Метод для маппинга /{id}
     */
    @GetMapping(value = {"/{id}"}, produces = APPLICATION_JSON_VALUE)
    public OrganizationView getOrganizationById(@PathVariable(name = "id") Integer id) {
        return organizationService.getById(id);
    }

    /**
     * Метод для маппинга /update
     */
    @PostMapping("/update")
    public void updateOrganization(@RequestBody @Validated(OrganizationView.Update.class) OrganizationView organizationView,
                                   BindingResult result) {
        Validator.validate(result);
        organizationService.update(organizationView);
    }

    /**
     * Метод для маппинга /save
     */
    @PostMapping("/save")
    public void createOrganization(@RequestBody @Validated(OrganizationView.Save.class) OrganizationView organizationView,
                                   BindingResult result) {
        Validator.validate(result);
        organizationService.save(organizationView);
    }
}
