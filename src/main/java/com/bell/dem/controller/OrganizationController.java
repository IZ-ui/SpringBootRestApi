package com.bell.dem.controller;

import com.bell.dem.service.OrganizationService;
import com.bell.dem.view.OfficeOutView;
import com.bell.dem.view.OrganizationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public List<OfficeOutView> getOrganizationByFilter(@Validated(OrganizationView.List.class)
                                                       @RequestBody OrganizationView organization) {
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
    @PostMapping(value = {"/update"}, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public void updateOrganization(@RequestBody @Validated(OrganizationView.Update.class) OrganizationView organizationView) {
        organizationService.update(organizationView);
    }

    /**
     * Метод для маппинга /save
     */
    @PostMapping(value = {"/save"}, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public void createOrganization(@RequestBody @Validated(OrganizationView.Save.class) OrganizationView organizationView) {
        organizationService.save(organizationView);
    }
}
