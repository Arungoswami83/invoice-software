package com.amstech.invoice.service.converter.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.response.model.CompanyResponseModel;



@Component
public class CompanyEntityToModelConverter {

	
	
	public List<CompanyResponseModel> getFindAllConvert(List<Company> companyList){

		 List<CompanyResponseModel> companyResponseModels = new ArrayList<>();

	        for (Company company : companyList) {
	            CompanyResponseModel responseModel = new CompanyResponseModel();
	            responseModel.setId(company.getId());
	            responseModel.setName(company.getName());
	            responseModel.setCinNo(company.getCinNo());
	            responseModel.setRegistrationNo(company.getRegistrationNo());
	            responseModel.setEmail(company.getEmail());
	            responseModel.setCompanyPhone(company.getCompanyPhone());
	            responseModel.setWebsite(company.getWebsite());
	            responseModel.setAddress(company.getAddress());
	            responseModel.setBusinessTypesId(company.getBusinessTypes().getId());
	            responseModel.setTaxIdentificationNumber(company.getTaxIdentificationNumber());
	            responseModel.setTaxPayer(company.getTaxPayer());
	            responseModel.setLogo(company.getLogo());
	            responseModel.setClientId(company.getClient().getId());
	            

	            // Corrected: Convert byte to boolean
	            responseModel.setIsDeleted(company.getIsDeleted() != (byte) 0);
	            responseModel.setUpdatedAt(Timestamp.from(Instant.now()));
	            //responseModel.setUpdatedAt(company.getUpdatedAt());

	            companyResponseModels.add(responseModel);
	        }
	        return companyResponseModels;

	}
	public CompanyResponseModel getfindById(Company company){
		
	
	        CompanyResponseModel responseModel = new CompanyResponseModel();

	        responseModel.setId(company.getId());
	        responseModel.setName(company.getName());
	        responseModel.setCinNo(company.getCinNo());
	        responseModel.setRegistrationNo(company.getRegistrationNo());
	        responseModel.setEmail(company.getEmail());
	        responseModel.setCompanyPhone(company.getCompanyPhone());
	        responseModel.setWebsite(company.getWebsite());
	        responseModel.setAddress(company.getAddress());
	        responseModel.setBusinessTypesId(company.getBusinessTypes().getId());
	        responseModel.setClientId(company.getClient().getId());
	        responseModel.setTaxIdentificationNumber(company.getTaxIdentificationNumber());
	        responseModel.setTaxPayer(company.getTaxPayer());
	        responseModel.setLogo(company.getLogo());
	       responseModel.setIsDeleted(company.getIsDeleted() == 1);  
	        responseModel.setUpdatedAt(Timestamp.from(Instant.now()));

		return responseModel;

	}


}
