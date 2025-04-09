package com.amstech.invoice.service.service;

import org.springframework.stereotype.Service;

import com.amstech.invoice.service.entity.BusinessTypes;
import com.amstech.invoice.service.repo.BusinessTypesRepo;
import com.amstech.invoice.service.response.model.BusinessTypesResponseModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessTypesService {
    
    private  BusinessTypesRepo businessTypesRepo;

    public BusinessTypesService(BusinessTypesRepo businessTypesRepo) {
        this.businessTypesRepo = businessTypesRepo;
    }

    public List<BusinessTypesResponseModel> getAllBusinessTypes() {
        List<BusinessTypesResponseModel> responseList = new ArrayList<>();
        
        try {
            List<BusinessTypes> businessTypes = businessTypesRepo.findAll();

            if (businessTypes != null && !businessTypes.isEmpty()) {
                for (BusinessTypes bt : businessTypes) {
                    BusinessTypesResponseModel response = new BusinessTypesResponseModel(bt.getId(), bt.getName());
                    responseList.add(response);
                }
            } else {
                System.out.println("No business types found.");
            }
        } catch (Exception e) {
            System.err.println("Error while fetching business types: " + e.getMessage());
        }

        return responseList;
    }
}
