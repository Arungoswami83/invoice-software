package com.amstech.invoice.service.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.response.model.BusinessTypesResponseModel;
import com.amstech.invoice.service.service.BusinessTypesService;

@RestController
@RequestMapping("/api/business-types")
public class BusinessTypesController {

    private BusinessTypesService businessTypesService;

    public BusinessTypesController(BusinessTypesService businessTypesService) {
        this.businessTypesService = businessTypesService;
    }

    @GetMapping
    public List<BusinessTypesResponseModel> getAllBusinessTypes() {
        return businessTypesService.getAllBusinessTypes();
    }
}
