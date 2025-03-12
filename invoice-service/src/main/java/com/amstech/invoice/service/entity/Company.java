package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name = "is_email_update")
	private Boolean isEmailUpdate;


	@Lob
	private String address;

	@Column(name="admin_username")
	private String adminUsername;

	@Column(name="cin_no")
	private String cinNo;

	@Column(name="company_phone")
	private String companyPhone;

	@Column(name="created_at")
	private Timestamp createdAt;

	private String email;

	@Column(name="is_deleted")
	private byte isDeleted;

	@Column(name="is_email_verified")
	private byte isEmailVerified;

	@Lob
	private String logo;

	private String name;

	private String password;

	@Column(name="registration_no")
	private String registrationNo;

	
	@Column(name="tax_identification_number")
	private String taxIdentificationNumber;

	@Column(name="tax_payer")
	private String taxPayer;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	private String website;

	//bi-directional many-to-one association to BusinessType
	@ManyToOne
	@JoinColumn(name="business_type_id")
	private BusinessType businessType;

	//bi-directional many-to-one association to Client
	@ManyToOne
	private Client client;

	//bi-directional many-to-one association to Currency
	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="company")
	private List<Invoice> invoices;

	//bi-directional many-to-one association to ProformaInvoice
	@OneToMany(mappedBy="company")
	private List<ProformaInvoice> proformaInvoices;

	//bi-directional many-to-one association to StandardInvoice
	@OneToMany(mappedBy="company")
	private List<StandardInvoice> standardInvoices;

	public Company() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Boolean getIsEmailUpdate() {
		return isEmailUpdate;
	}

	public void setIsEmailUpdate(Boolean isEmailUpdate) {
		this.isEmailUpdate = isEmailUpdate;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAdminUsername() {
		return this.adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getCinNo() {
		return this.cinNo;
	}

	public void setCinNo(String cinNo) {
		this.cinNo = cinNo;
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

	public byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(byte isDeleted) {
		this.isDeleted = isDeleted;
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

	public BusinessType getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
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

	public List<ProformaInvoice> getProformaInvoices() {
		return this.proformaInvoices;
	}

	public void setProformaInvoices(List<ProformaInvoice> proformaInvoices) {
		this.proformaInvoices = proformaInvoices;
	}

	public ProformaInvoice addProformaInvoice(ProformaInvoice proformaInvoice) {
		getProformaInvoices().add(proformaInvoice);
		proformaInvoice.setCompany(this);

		return proformaInvoice;
	}

	public ProformaInvoice removeProformaInvoice(ProformaInvoice proformaInvoice) {
		getProformaInvoices().remove(proformaInvoice);
		proformaInvoice.setCompany(null);

		return proformaInvoice;
	}

	public List<StandardInvoice> getStandardInvoices() {
		return this.standardInvoices;
	}

	public void setStandardInvoices(List<StandardInvoice> standardInvoices) {
		this.standardInvoices = standardInvoices;
	}

	public StandardInvoice addStandardInvoice(StandardInvoice standardInvoice) {
		getStandardInvoices().add(standardInvoice);
		standardInvoice.setCompany(this);

		return standardInvoice;
	}

	public StandardInvoice removeStandardInvoice(StandardInvoice standardInvoice) {
		getStandardInvoices().remove(standardInvoice);
		standardInvoice.setCompany(null);

		return standardInvoice;
	}

}