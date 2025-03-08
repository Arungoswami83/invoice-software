package com.amstech.invoice.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.request.model.CompanyLoginRequestModel;
import com.amstech.invoice.service.request.model.CompanySignupRequestModel;
import com.amstech.invoice.service.request.model.CompanyUpdateRequestModel;
import com.amstech.invoice.service.response.message.RestResponse;
import com.amstech.invoice.service.response.model.ClientResponseModel;
import com.amstech.invoice.service.response.model.CompanyResponseModel;
import com.amstech.invoice.service.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	  private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	    
	@Autowired
	private  CompanyService companyService;

    
    public CompanyController() {
        
        System.out.println("InvoiceCompanyController: Object Created");
}
    @Operation(summary = "you can use this method for company/signup",description = "this is company signup")
    @RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
    public RestResponse signup(@RequestBody CompanySignupRequestModel companyRequestModel) {
        logger.info("Saving user data: {}", companyRequestModel.getName());
        try {
            companyService.signup(companyRequestModel);
            return RestResponse.build().data(companyRequestModel).success(1452).message("company registyration successfully");
        } catch (Exception e) {
           e.printStackTrace();
            return RestResponse.build().error(1457).message("failed to save company data due to"+e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login", produces = "application/json")
    public RestResponse companyLogin(@RequestBody CompanyLoginRequestModel companyLoginRequestModel) {
        logger.info("Login attempt for company with password: {}", companyLoginRequestModel.getPassword());
        try {
            companyService.login(companyLoginRequestModel);
            return RestResponse.build().data(companyLoginRequestModel).success(1452).message("company login successfully");
        } catch (Exception e) {
        	 e.printStackTrace();
             return RestResponse.build().error(1457).message("failed to login company data due to"+e.getMessage());
         }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
    public RestResponse update(@RequestBody CompanyUpdateRequestModel updateRequestModel) {
        logger.info("Updating company details with ID: {}", updateRequestModel.getId());
        try {
            companyService.updateCompany(updateRequestModel);
            return RestResponse.build().data(updateRequestModel).success(1452).message("company updated successfully");
            
        } catch (Exception e) {
           e.printStackTrace();
        return RestResponse.build().error(1457).message("failed to update company data due to"+e.getMessage());
    }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/softDeleteById", produces = "application/json")
    public RestResponse softDeleteById(@RequestParam Integer id) {
        logger.info("Soft deleting company with ID: {}", id);
        try {
            companyService.softDeleteById(id);
            return RestResponse.build().success(1452).message("company deleted successfully");
        } catch (Exception e) {
        	 e.printStackTrace();
             return RestResponse.build().error(1457).message("failed to deleted company data due to"+e.getMessage());
         }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findById", produces = "application/json")
    public RestResponse findById(@RequestParam("id") int id) {
        logger.info("Fetching company details for ID: {}", id);
        try {
            CompanyResponseModel companyResponseModel = companyService.findByCompanyId(id);
            return RestResponse.build().data(companyResponseModel).success(1452).message("company found successfully");
        } catch (Exception e) {
        	 e.printStackTrace();
             return RestResponse.build().error(1457).message("failed to found company data due to"+e.getMessage());
         }
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/findAll", produces = "application/json")
    public ResponseEntity<RestResponse> findAllCompanies(@RequestParam("page") Integer page, 
                                                         @RequestParam("size") Integer size) {
        logger.info("Fetching all company details, page: {}, size: {}", page, size);

        try {
            List<CompanyResponseModel> companyResponseModels = companyService.findAllCompanies(page, size);
            long totalRecord = companyService.countAllCompany();

            RestResponse response = RestResponse.build()
                    .data(companyResponseModels)
                    .success(200)
                    .page(page)
                    .size(size)
                    .totalRecord(totalRecord)
                    .message("Companies retrieved successfully");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Error fetching company data", e);

            RestResponse errorResponse = RestResponse.build()
                    .error(500)
                    .message("Failed to retrieve company data: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
   
		    
//    @RequestMapping(method = RequestMethod.GET, value = "/findAll", produces = "application/json")
//    public ResponseEntity<RestResponse> findAllCompanies(@RequestParam("page") Integer page, 
//    		 @RequestParam("size") Integer size) {
//    			   logger.info("Fetching all company details, page: {}, size: {}", page, size);
//
//        try {
//            List<CompanyResponseModel> companyResponseModels = companyService.findAllCompanies(page,size);
//            long totalRecord = companyService.count();
//            RestResponse response = RestResponse.build() .data(companyResponseModels).success(200) 
//           		 .page(page).size(size).totalRecord(totalRecord).message("company retrieved successfully");
//
//           		            return ResponseEntity.ok(response);
//
//           		            } catch (Exception e) {
//           		                logger.error("Error fetching comapny data", e);
//
//           		                RestResponse errorResponse = RestResponse.build()
//           		                        .error(500) 
//           		                        .message("Failed to retrieve company data: " + e.getMessage());
//
//           		                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//           		            }
//           		        }
//}
