package com.bell.dem.controller;

import com.bell.dem.service.DocTypeService;
import com.bell.dem.view.DocTypeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller для DocType
 */
@RestController
@RequestMapping(value = "/api/docs", produces = APPLICATION_JSON_VALUE)
public class DocTypeController {

    private final DocTypeService docTypeService;

    @Autowired
    public DocTypeController(DocTypeService docTypeService) {
        this.docTypeService = docTypeService;
    }

    /**
     * Метод для маппинга /
     */
    @GetMapping
    public List<DocTypeView> docs(){
        return docTypeService.docs();
    }
}
