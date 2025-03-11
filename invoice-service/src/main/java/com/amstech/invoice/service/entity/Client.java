package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the client database table.
 * 
 */
@Entity
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
@Table(name="client")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@JsonProperty
	@Column(name="username")
	private String userName;
	@JsonProperty
	@Column(name="password",nullable = false)
	private String password;
	
	@Lob
	private String address;
	@Column(name = "phone_number") // Adjust if needed
	private String phoneNumber;
	@Lob
	@Column(name="billing_address")
	private String billingAddress;

	@Column(name="business_name")
	private String businessName;

	@Column(name="company_name")
	private String companyName;

	@Column(name="created_at")
	private Timestamp createdAt;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean isDeleted = false;


	@Column(name="last_name")
	private String lastName;

	@Column(name="linkedin_profile_url")
	private String linkedinProfileUrl;

	@Column(name="mobile_number")
	private String mobileNumber;

	@Column(name="pan_number")
	private String panNumber;

	@Column(name="postal_zip_code")
	private String postalZipCode;

	@Lob
	@Column(name="specific_registration_details")
	private String specificRegistrationDetails;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	//bi-directional many-to-one association to City
	 
	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
	private City city;


	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="client")
	private List<Invoice> invoices;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="client")
	private List<Report> reports;

	public Client() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBillingAddress() {
		return this.billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLinkedinProfileUrl() {
		return this.linkedinProfileUrl;
	}

	public void setLinkedinProfileUrl(String linkedinProfileUrl) {
		this.linkedinProfileUrl = linkedinProfileUrl;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPanNumber() {
		return this.panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getPostalZipCode() {
		return this.postalZipCode;
	}

	public void setPostalZipCode(String postalZipCode) {
		this.postalZipCode = postalZipCode;
	}

	public String getSpecificRegistrationDetails() {
		return this.specificRegistrationDetails;
	}

	public void setSpecificRegistrationDetails(String specificRegistrationDetails) {
		this.specificRegistrationDetails = specificRegistrationDetails;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Invoice addInvoice(Invoice invoice) {
		getInvoices().add(invoice);
		invoice.setClient(this);

		return invoice;
	}

	public Invoice removeInvoice(Invoice invoice) {
		getInvoices().remove(invoice);
		invoice.setClient(null);

		return invoice;
	}

	public List<Report> getReports() {
		return this.reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setClient(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setClient(null);

		return report;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	

	public City getCity() {
		return city;
	}
	

	public void setCity(City city) {
		this.city = city;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}