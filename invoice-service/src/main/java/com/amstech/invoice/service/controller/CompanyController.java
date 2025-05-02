package com.amstech.invoice.service.controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.amstech.invoice.service.request.model.CompanyLoginRequestModel;
import com.amstech.invoice.service.request.model.CompanySignupRequestModel;
import com.amstech.invoice.service.request.model.CompanyUpdateRequestModel;
import com.amstech.invoice.service.response.RestResponse;
import com.amstech.invoice.service.response.model.ClientResponseModel;
import com.amstech.invoice.service.response.model.CompanyResponseModel;
import com.amstech.invoice.service.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;


@RestController
@RequestMapping("/company")
public class CompanyController {
	
	  private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	    
	@Autowired
	private  CompanyService companyService;
    
    public CompanyController() {
    	logger.info("CompanyController: Object Created");
}

    private static final String UPLOAD_DIR = "D:/uploads/";  
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/signup", consumes = {"multipart/form-data"})
    public ResponseEntity<String> registerCompany(
            @RequestPart(value = "company") String companyJson,
            @RequestPart(value = "logo", required = false) MultipartFile file) {

        try {
            System.out.println("🔹 Received JSON: " + companyJson);

            
            CompanySignupRequestModel company = objectMapper.readValue(companyJson, CompanySignupRequestModel.class);
            System.out.println(" Parsed Company Object: " + company);

            
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists() && !uploadDir.mkdirs()) {
                System.out.println(" Failed to create upload directory!");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(" Unable to create upload directory.");
            }

           
            if (file != null && !file.isEmpty()) {
                String uniqueFilename = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR, uniqueFilename);
                file.transferTo(filePath.toFile()); 
                company.setLogo(uniqueFilename);
                System.out.println("📂 File uploaded successfully: " + filePath.toAbsolutePath());
            } else {
                System.out.println("⚠️ No file uploaded.");
            }

            System.out.println("✅ Final Company Object with Logo: " + company);
            return ResponseEntity.ok("✅ Company Registered Successfully with Logo: " + company.getLogo());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(" Error processing request: " + e.getMessage());
        }
    }

  @Operation(summary = "you can use this method for company/signup",description = "this is company signup")
    @RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = "application/json", produces = "application/json")
    public RestResponse signup(@RequestBody CompanySignupRequestModel companySignupRequestModel) {
        logger.info("Saving Company data with name: {}", companySignupRequestModel.getName());
        try {
            CompanyResponseModel companyResponseModel = companyService.signup(companySignupRequestModel); 
            return RestResponse.build().withSuccess("company registyration successfully",companyResponseModel);
        } catch (Exception e) {
        	logger.error("Failed to save company due to: {}", e.getMessage(), e);
        	return RestResponse.build().withError(e.getMessage());
      }
        }

    @RequestMapping(method = RequestMethod.POST, value = "/login", consumes = "application/json", produces = "application/json")
    public RestResponse companyLogin(@RequestBody CompanyLoginRequestModel companyLoginRequestModel) {
      
        try {
        	  logger.info("Login with username: {}", companyLoginRequestModel.getAdminUsername());
             CompanyResponseModel companyResponseModel = companyService.login(companyLoginRequestModel);
            return RestResponse.build().withSuccess("company login successfully",companyResponseModel);
        } catch (Exception e) {
        	 logger.error("Failed to login company due to: {}", e.getMessage(), e);
             return RestResponse.build().withError(e.getMessage());
         }
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json", produces = "application/json")
    public RestResponse update(@RequestBody CompanyUpdateRequestModel companyupdateRequestModel) {
        logger.info("Updating company details with ID: {}", companyupdateRequestModel.getId());
        try {
           CompanyResponseModel companyResponseModel= companyService.updatecompany(companyupdateRequestModel);
            return RestResponse.build().withSuccess("company updated successfully",companyResponseModel);
            
        } catch (Exception e) {
        	logger.error("Failed to update company due to: {}", e.getMessage(), e);
        return RestResponse.build().withError(e.getMessage());
    }
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/softDeleteById", produces = "application/json")
    public RestResponse softDeleteById(@RequestParam ("id")Integer id) {
        logger.info("Soft deleting company with ID: {}", id);
        try {
            companyService.softDeleteById(id);
            return RestResponse.build().withSuccess("company deleted successfully");
        } catch (Exception e) {
        	 logger.error("Failed to delete company due to: {}", e.getMessage(), e);
             return RestResponse.build().withError(e.getMessage());
         }
    }

    @Operation(summary = "Restore a soft-deleted company by ID")
    @PutMapping("/restoreById")
    public ResponseEntity<String> restoreCompany(
        @Parameter(description = "ID of the company to restore", required = true)
        @RequestParam Integer id) {
        try {
            companyService.restoreById(id);
            return ResponseEntity.ok("Company restored successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
  
    @RequestMapping(method = RequestMethod.GET, value = "/findById", produces = "application/json")
    public RestResponse findById(@RequestParam("id") int id) {
        logger.info("Fetching company details for ID: {}", id);
        try {
            CompanyResponseModel companyResponseModel = companyService.findByCompanyId(id);
            return RestResponse.build().withSuccess("company found successfully",companyResponseModel);
        } catch (Exception e) {
        	 logger.error("Failed to find user due to: {}", e.getMessage(), e);
             return RestResponse.build().withError(e.getMessage());
         }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAll", produces = "application/json")
    public RestResponse findAllCompanies(@RequestParam("page") Integer page,@RequestParam("size") Integer size) {
        logger.info("Fetching all company details, page: {}, size: {}");
        try {
            List<CompanyResponseModel> companyResponseModels = companyService.findAllCompanies(page, size);
            long totalRecord = companyService.countAllCompany();
            return RestResponse.build().withSuccess("Companies retrieved successfully").withTotalRecords(totalRecord).withPageNumber(page).withPageSize(size).withData(companyResponseModels);
        } catch (Exception e) {
            logger.error("Failed to find company list due to: {}", e);
          return RestResponse.build().withError(e.getMessage());
        }
    }
}
   
	
    
 