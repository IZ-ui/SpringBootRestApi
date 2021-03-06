package com.bell.dem.controller;

import com.bell.dem.service.OfficeService;
import com.bell.dem.util.Validator;
import com.bell.dem.view.OfficeInView;
import com.bell.dem.view.OrgOffShortView;
import com.bell.dem.view.OfficeOutView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller для Office
 */
@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Метод для маппинга /list
     */
    @PostMapping("/list")
    public List<OrgOffShortView> getOfficeByFilter(@Validated(OfficeInView.List.class)
                                                   @RequestBody OfficeInView officeInView,
                                                   BindingResult result) {
        Validator.validate(result);
        return officeService.getByFilter(officeInView);
    }

    /**
     * Метод для маппинга /{id}
     */
    @GetMapping(value = {"/{id}"}, produces = APPLICATION_JSON_VALUE)
    public OfficeOutView getOfficeById(@PathVariable(name = "id") Integer id) {
        return officeService.getByID(id);
    }

    /**
     * Метод для маппинга /update
     */
    @PostMapping(value = {"/update"}, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public void updateOffice(@RequestBody @Validated(OfficeInView.Update.class) OfficeInView officeInView,
                             BindingResult result) {
        Validator.validate(result);
        officeService.update(officeInView);
    }

    /**
     * Метод для маппинга /save
     */
    @PostMapping(value = {"/save"}, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public void createOffice(@RequestBody @Validated(OfficeInView.Save.class) OfficeInView officeInView,
                             BindingResult result) {
        Validator.validate(result);
        officeService.save(officeInView);
    }
}
