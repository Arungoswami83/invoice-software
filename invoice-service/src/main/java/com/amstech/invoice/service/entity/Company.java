package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the company database table.
 */
@Entity
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    public Boolean getIsEmailUpdate() {
		return isEmailUpdate;
	}

	public void setIsEmailUpdate(Boolean isEmailUpdate) {
		this.isEmailUpdate = isEmailUpdate;
	}

	@Column(name = "is_email_update", nullable = false)
    private Boolean isEmailUpdate = false;

    @Lob
    private String address;

    @Column(name = "admin_username", nullable = false, unique = true)
    private String adminUserName;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name="business_types_id", nullable = false)
    private BusinessTypes businessTypes;

    @Column(name="cin_no")
    private String cinNo;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name="company_phone")
    private String companyPhone;

    @Column(name="created_at")
    private Timestamp createdAt;

    private String email;

    @Column(name = "is_deleted", columnDefinition = "TINYINT(1) DEFAULT 0")
    private Byte isDeleted = 0;

    @Column(name="is_email_verified")
    private byte isEmailVerified;

    @Lob
    private String logo;

    private String name;

    @Column(name="registration_no")
    private String registrationNo;

    @Column(name="tax_identification_number")
    private String taxIdentificationNumber;

    @Column(name="tax_payer")
    private String taxPayer;

    @Column(name="updated_at")
    private Timestamp updatedAt;

    private String website;

    @OneToMany(mappedBy="company")
    private List<Invoice> invoices;
    
    public Company() {
    	
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEmailUpdate() {
        return isEmailUpdate;
    }

    public void setEmailUpdate(boolean isEmailUpdate) {
        this.isEmailUpdate = isEmailUpdate;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdminUserName() {
        return this.adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public BusinessTypes getBusinessTypes() {
        return businessTypes;
    }

    public void setBusinessTypes(BusinessTypes businessTypes) {
        this.businessTypes = businessTypes;
    }

    public String getCinNo() {
        return this.cinNo;
    }

    public void setCinNo(String cinNo) {
        this.cinNo = cinNo;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getCompanyPhone() {
        return this.companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getIsEmailVerified() {
        return this.isEmailVerified;
    }

    public void setIsEmailVerified(byte isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationNo() {
        return this.registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getTaxIdentificationNumber() {
        return this.taxIdentificationNumber;
    }

    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    public String getTaxPayer() {
        return this.taxPayer;
    }

    public void setTaxPayer(String taxPayer) {
        this.taxPayer = taxPayer;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Invoice> getInvoices() {
        return this.invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Invoice addInvoice(Invoice invoice) {
        getInvoices().add(invoice);
        invoice.setCompany(this);
        return invoice;
    }

    public Invoice removeInvoice(Invoice invoice) {
        getInvoices().remove(invoice);
        invoice.setCompany(null);
        return invoice;
    }
}