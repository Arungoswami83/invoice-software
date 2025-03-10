package com.amstech.invoice.service.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amstech.invoice.service.entity.BusinessTypes;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.Currency;
import com.amstech.invoice.service.repo.BusinessTypesRepo;
import com.amstech.invoice.service.repo.CompanyRepo;
import com.amstech.invoice.service.repo.CurrencyRepo;
import com.amstech.invoice.service.request.model.CompanyLoginRequestModel;
import com.amstech.invoice.service.request.model.CompanySignupRequestModel;
import com.amstech.invoice.service.request.model.CompanyUpdateRequestModel;
import com.amstech.invoice.service.response.model.ClientResponseModel;
import com.amstech.invoice.service.response.model.CompanyResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private CurrencyRepo currencyRepo;
	@Autowired
	private BusinessTypesRepo businessTypesRepo;
	
	    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

	    public void signup(CompanySignupRequestModel companySignupRequestModel) throws Exception {
	        logger.info("Starting company signup process for email: {}", companySignupRequestModel.getEmail());

	        
	       

	        Optional<BusinessTypes> businessTypesOptional = businessTypesRepo.findById(companySignupRequestModel.getBusinessTypesId());
	        if (!businessTypesOptional.isPresent()) {
	            logger.error("Business Type does not exist for ID: {}", companySignupRequestModel.getBusinessTypesId());
	            throw new Exception("Business Type does not exist.");
	        }

	        if (companyRepo.findByEmail(companySignupRequestModel.getEmail()) != null) {
	            logger.warn("Email already exists: {}", companySignupRequestModel.getEmail());
	            throw new Exception("Email already exists. Please try another email.");
	        }

	        if (companyRepo.findByCompanyPhone(companySignupRequestModel.getCompanyPhone()) != null) {
	            logger.warn("Company phone number already exists: {}", companySignupRequestModel.getCompanyPhone());
	            throw new Exception("Company phone number already exists. Please try another number.");
	        }
	      
		 
	        Company company = new Company();
	        company.setEmail(companySignupRequestModel.getEmail());
	        company.setName(companySignupRequestModel.getName());
	        company.setPassword(companySignupRequestModel.getPassword());
	        company.setAddress(companySignupRequestModel.getAddress());
	        company.setAdminUserName(companySignupRequestModel.getAdminUserName());
	        company.setBusinessTypesId(companySignupRequestModel.getBusinessTypesId());
	        company.setCinNo(companySignupRequestModel.getCinNo());
	        company.setClientId(companySignupRequestModel.getClientId());
	        company.setCompanyPhone(companySignupRequestModel.getCompanyPhone());
	        company.setWebsite(companySignupRequestModel.getWebsite());
	        company.setLogo(companySignupRequestModel.getLogo());
	        company.setRegistrationNo(companySignupRequestModel.getRegistrationNo());
	        company.setTaxIdentificationNumber(companySignupRequestModel.getTaxIdentificationNumber());
	        company.setTaxPayer(companySignupRequestModel.getTaxPayer());
	        company.setCreatedAt(Timestamp.from(Instant.now())); 
	        //company.setUpdatedAt(Timestamp.from(Instant.now()));
	        company.setEmailUpdate(true);
	        company.setIsEmailVerified((byte) 1);
	      //  company.setIsDeleted((byte) 0);
	       // company.setRestore(1);
	        

	        Company savedCompany = companyRepo.save(company);
	        logger.info("Company successfully registered with ID: {}", savedCompany.getId());
	    }

	    public void login(CompanyLoginRequestModel companyLoginRequestModel) throws Exception {
	        logger.info("Attempting login for admin: {}", companyLoginRequestModel.getAdminUsername());
	        Company company = companyRepo.findByAdminUserNameAndPassword(
	            companyLoginRequestModel.getAdminUsername(),
	            companyLoginRequestModel.getPassword()
	        );

	        if (company == null) {
	            logger.warn("Invalid login attempt for admin: {}", companyLoginRequestModel.getAdminUsername());
	            throw new Exception("Wrong admin username or password.");
	        }

	        if (company.getIsDeleted() == (byte) 1)  {
	            logger.warn("Attempt to login into deactivated account: {}", companyLoginRequestModel.getAdminUsername());
	            throw new Exception("This company account is deactivated.");
	        }
	        logger.info("Login successful for admin: {}", companyLoginRequestModel.getAdminUsername());
	    }

	    public void softDeleteById(Integer id) throws Exception {
	        logger.info("Initiating soft delete for company ID: {}", id);
	        Optional<Company> companyOptional = companyRepo.findById(id);
	        
	        if (!companyOptional.isPresent()) {
	            logger.error("Attempted to delete non-existing company ID: {}", id);
	            throw new Exception("Company does not exist.");
	        }

	        Company company = companyOptional.get();
	        if (company.getIsDeleted() == (byte) 1) {
	            logger.warn("Company ID {} is already deleted.", id);
	            throw new Exception("Company already deleted.");
	        }

	        company.setIsDeleted((byte) 1);  // false means 0 (not deleted)
	        companyRepo.save(company);
	        logger.info("Company ID {} successfully soft deleted.", id);

	    }
	
	    
	    public String restoreById(Integer id) {
	        Company company = companyRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Company not found"));

	        if (company.getIsDeleted() == 0) {
	            throw new RuntimeException("Company is already active");
	        }

	        company.setIsDeleted((byte) 0);
	        companyRepo.save(company);
	        return "Company restored successfully";
	    }
	


	    public void updateCompany(CompanyUpdateRequestModel companyUpdateRequestModel) throws Exception {
	        logger.info("Updating company with ID: {}", companyUpdateRequestModel.getId());

	        Optional<Company> companyOptional = companyRepo.findById(companyUpdateRequestModel.getId());
	        if (!companyOptional.isPresent()) {
	            logger.error("Company with ID {} does not exist.", companyUpdateRequestModel.getId());
	            throw new Exception("Company does not exist.");
	        }

	        Company company = companyOptional.get();

	        if (companyUpdateRequestModel.isEmailUpdate()) {
	            if (!companyUpdateRequestModel.isEmailVerified()) {
	                logger.warn("Email update requested but not verified for company ID: {}", company.getId());
	                throw new Exception("Email is not verified, please verify first.");
	            }
	            Company companyByEmail = companyRepo.findByEmail(companyUpdateRequestModel.getEmail());
	            if (companyByEmail != null) {
	                logger.error("Email {} is already in use.", companyUpdateRequestModel.getEmail());
	                throw new Exception("Email is already in use.");
	            }
	            company.setEmail(companyUpdateRequestModel.getEmail());
	        }

	        if (companyUpdateRequestModel.getName() != null) {
	            logger.info("Updating name for company ID: {}", company.getId());
	            company.setName(companyUpdateRequestModel.getName());
	        }

	        if (companyUpdateRequestModel.getAddress() != null) {
	            logger.info("Updating address for company ID: {}", company.getId());
	            company.setAddress(companyUpdateRequestModel.getAddress());
	        }

	        if (companyUpdateRequestModel.getBusinessTypesId() != 0) {
	            logger.info("Updating business type for company ID: {}", company.getId());
	            company.setBusinessTypesId(companyUpdateRequestModel.getBusinessTypesId());
	        }

	        if (companyUpdateRequestModel.getCompanyPhone() != null) {
	            logger.info("Updating phone number for company ID: {}", company.getId());
	            company.setCompanyPhone(companyUpdateRequestModel.getCompanyPhone());
	        }

	        if (companyUpdateRequestModel.getLogo() != null) {
	            logger.info("Updating logo for company ID: {}", company.getId());
	            company.setLogo(companyUpdateRequestModel.getLogo());
	        }

	        if (companyUpdateRequestModel.getPassword() != null) {
	            logger.info("Updating password for company ID: {}", company.getId());
	            company.setPassword(companyUpdateRequestModel.getPassword());
	        }

	        if (companyUpdateRequestModel.getTaxPayer() != null) {
	            logger.info("Updating tax payer status for company ID: {}", company.getId());
	            company.setTaxPayer(companyUpdateRequestModel.getTaxPayer());
	        }

	        if (companyUpdateRequestModel.getWebsite() != null) {
	            logger.info("Updating website for company ID: {}", company.getId());
	            company.setWebsite(companyUpdateRequestModel.getWebsite());
	        }

	        companyRepo.save(company);
	        logger.info("Successfully updated company with ID: {}", company.getId());
	    }

	    public CompanyResponseModel findByCompanyId(Integer companyId) throws Exception {
	        logger.info("Fetching details for company ID: {}", companyId);

	        Optional<Company> companyOptional = companyRepo.findById(companyId);
	        if (!companyOptional.isPresent()) {
	            logger.error("Company with ID {} does not exist.", companyId);
	            throw new Exception("Company does not exist.");
	        }

	        Company company = companyOptional.get();
	        CompanyResponseModel responseModel = new CompanyResponseModel();

	        responseModel.setId(company.getId());
	        responseModel.setName(company.getName());
	        responseModel.setCinNo(company.getCinNo());
	        responseModel.setRegistrationNo(company.getRegistrationNo());
	        responseModel.setEmail(company.getEmail());
	        responseModel.setCompanyPhone(company.getCompanyPhone());
	        responseModel.setWebsite(company.getWebsite());
	        responseModel.setAddress(company.getAddress());
	        responseModel.setBusinessTypesId(company.getBusinessTypesId());
	      
	        responseModel.setTaxIdentificationNumber(company.getTaxIdentificationNumber());
	        responseModel.setTaxPayer(company.getTaxPayer());
	        responseModel.setLogo(company.getLogo());
	       // responseModel.setIsDeleted(company.getIsDeleted() == 1);  // Convert byte (0/1) to Boolean
	        responseModel.setUpdatedAt(Timestamp.from(Instant.now()));

	        logger.info("Successfully fetched details for company ID: {}", companyId);
	        return responseModel;
	    }	
	    
	    public List<CompanyResponseModel> findAllCompanies(Integer page, Integer size) throws Exception {
	        logger.info("Fetching all active companies.");

	        // Fetch only active companies
	        Page<Company> companyPage = companyRepo.findAll(PageRequest.of(page, size));

	        List<CompanyResponseModel> companyResponseModels = new ArrayList<>();

	        for (Company company : companyPage.getContent()) {
	            CompanyResponseModel responseModel = new CompanyResponseModel();
	            responseModel.setId(company.getId());
	            responseModel.setName(company.getName());
	            responseModel.setCinNo(company.getCinNo());
	            responseModel.setRegistrationNo(company.getRegistrationNo());
	            responseModel.setEmail(company.getEmail());
	            responseModel.setCompanyPhone(company.getCompanyPhone());
	            responseModel.setWebsite(company.getWebsite());
	            responseModel.setAddress(company.getAddress());
	            responseModel.setBusinessTypesId(company.getBusinessTypesId());
	            responseModel.setTaxIdentificationNumber(company.getTaxIdentificationNumber());
	            responseModel.setTaxPayer(company.getTaxPayer());
	            responseModel.setLogo(company.getLogo());

	            // Corrected: Convert byte to boolean
	            responseModel.setIsDeleted(company.getIsDeleted() != (byte) 0);
	            responseModel.setUpdatedAt(Timestamp.from(Instant.now()));
	            //responseModel.setUpdatedAt(company.getUpdatedAt());

	            companyResponseModels.add(responseModel);
	        }

	        logger.info("Total active companies fetched: {}", companyResponseModels.size());
	        return companyResponseModels;
	    }

	    public long countAllCompany() throws Exception {
	        return companyRepo.countAllCompany();
	    }
}
		
	    
//	    public List<CompanyResponseModel> findAllCompanies(Integer page , Integer size) throws Exception {
//	        logger.info("Fetching all company details");
//	        
//	        List<Company> companies = companyRepo.findAll();
//	       
//	        List<CompanyResponseModel> responseModels = new ArrayList<>(); 
//	        
//	        for (Company company : companies) {
//	     		   if(company.isDeleted() == true) {
//	     		    	 throw new Exception("Company is not active");
//	     		   }
//	            CompanyResponseModel responseModel = new CompanyResponseModel();
//	            responseModel.setId(company.getId());
//	            responseModel.setName(company.getName());
//	            responseModel.setCinNo(company.getCinNo());
//	            responseModel.setRegistrationNo(company.getRegistrationNo());
//	            responseModel.setEmail(company.getEmail());
//	            responseModel.setCompanyPhone(company.getCompanyPhone());
//	            responseModel.setWebsite(company.getWebsite());
//	            responseModel.setAddress(company.getAddress());
//	            responseModel.setBusinessTypesId(company.getBusinessTypesId());
//	            responseModel.setCurrencyId(company.getCurrencyId());
//	            responseModel.setTaxIdentificationNumber(company.getTaxIdentificationNumber());
//	            responseModel.setTaxPayer(company.getTaxPayer());
//	            responseModel.setLogo(company.getLogo());
//	            responseModel.setIsDeleted(company.isDeleted());
//	            responseModel.setUpdatedAt(company.getUpdatedAt());
//	            responseModels.add(responseModel);
//	        }
//	        logger.info("Total active Companies fetched: {}", responseModels.size());
//	            return responseModels;
//	    }
//        public long count() throws Exception{
//        	return companyRepo.count();
//        }
//
//        }
