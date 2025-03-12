package com.amstech.invoice.service.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.amstech.invoice.service.converter.entity.CompanyModelToEntityConverter;
import com.amstech.invoice.service.converter.model.CompanyEntityToModelConverter;
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
	private BusinessTypesRepo businessTypesRepo;
	
	@Autowired
	private CompanyModelToEntityConverter companyModelToEntityConverter;
	
	@Autowired
	private CompanyEntityToModelConverter companyEntityToModelConverter;
	
	    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);
	    
 public CompanyResponseModel signup(CompanySignupRequestModel companySignupRequestModel) throws Exception {
	    	
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
	      
		 	        
	        Company company = companyModelToEntityConverter.getSaveConvert(companySignupRequestModel);
	        Company savedCompany = companyRepo.save(company);
	        CompanyResponseModel companyResponseModel = new CompanyResponseModel();
	       
	        return companyEntityToModelConverter.getfindById(savedCompany);

	    }


	    public CompanyResponseModel login(CompanyLoginRequestModel companyLoginRequestModel) throws Exception {
	        
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
	        return companyEntityToModelConverter.getfindById(company);
	    }

	    public void softDeleteById(Integer id) throws Exception {
	       
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

	        company.setIsDeleted((byte) 1); 
	        company.setUpdatedAt(Timestamp.from(Instant.now()));

	        companyRepo.save(company);
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
	


	    public CompanyResponseModel updatecompany(CompanyUpdateRequestModel companyUpdateRequestModel) throws Exception {

	        Optional<Company> companyOptional = companyRepo.findById(companyUpdateRequestModel.getId());
	        if (!companyOptional.isPresent()) {

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

	     Company savedCompany=   companyRepo.save(company);
	        return companyEntityToModelConverter.getfindById(savedCompany);
	    }
	    
	  public CompanyResponseModel findByCompanyId(Integer companyId) throws Exception {
	       
	    	Optional<Company> companyOptional = companyRepo.findById(companyId);
	        if (!companyOptional.isPresent()) {
	            logger.error("Company with ID {} does not exist.", companyId);
	            throw new Exception("Company does not exist.");
	        }

	        Company company = companyOptional.get();
	        if(company.getIsDeleted() == 1) {
	       throw new Exception("Your account has been deactivated. Please contact the administrator for assistance.");
	        }
	        return companyEntityToModelConverter.getfindById(company);
	    }	
	    

	    public List<CompanyResponseModel> findAllCompanies(Integer page, Integer size) throws Exception {
	    	 List<Company> companyList = companyRepo.findAllCompany(PageRequest.of(page, size));
	        return companyEntityToModelConverter.getFindAllConvert(companyList);
	    }

	    public long countAllCompany() throws Exception {
	        return companyRepo.countAllCompany();
	    }
}
		
	    

