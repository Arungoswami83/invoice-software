  package com.amstech.invoice.service.converter.entity;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.request.model.CompanySignupRequestModel;
import com.amstech.invoice.service.request.model.CompanyUpdateRequestModel;
@Component
public class CompanyModelToEntityConverter {
	
	public Company getSaveConvert(CompanySignupRequestModel companySignupRequestModel) {
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
	        
	         
	        return company;
	}
	
	public Company getUpdateConvert(CompanyUpdateRequestModel companyUpdateRequestModel, Company company) {
		company.setName(companyUpdateRequestModel.getName());
		company.setEmail(companyUpdateRequestModel.getEmail());
		company.setAddress(companyUpdateRequestModel.getAddress());
		company.setCompanyPhone(companyUpdateRequestModel.getCompanyPhone());
		company.setLogo(companyUpdateRequestModel.getLogo());
		company.setAdminUserName(companyUpdateRequestModel.getAdminUserName());
		company.setPassword(companyUpdateRequestModel.getPassword());
		company.setClientId(companyUpdateRequestModel.getClientId());
		company.setBusinessTypesId(companyUpdateRequestModel.getBusinessTypesId());

		
		return company;
	}

}
