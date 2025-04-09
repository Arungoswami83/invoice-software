package com.amstech.invoice.service.response.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;


public class CompanyResponseModel {
	
	private int id;
    private String name;
    private String cinNo;
    private String registrationNo;
    private String email;
    private String companyPhone;
    private String website;
    private String address;
    private int businessTypesId;
    private String taxIdentificationNumber;
    private String taxPayer;
    private String logo;
    private boolean isDeleted;
    private int clientId;
    
    
    
    
    public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "updated_at")
    private Timestamp updatedAt;

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCinNo() {
		return cinNo;
	}
	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getBusinessTypesId() {
		return businessTypesId;
	}
	public void setBusinessTypesId(int businessTypesId) {
		this.businessTypesId = businessTypesId;
	}
	public String getTaxIdentificationNumber() {
		return taxIdentificationNumber;
	}
	public void setTaxIdentificationNumber(String taxIdentificationNumber) {
		this.taxIdentificationNumber = taxIdentificationNumber;
	}
	public String getTaxPayer() {
		return taxPayer;
	}
	public void setTaxPayer(String taxPayer) {
		this.taxPayer = taxPayer;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public boolean setIsDeleted(boolean b) {
		return this.isDeleted = b;
	}
}
