package com.amstech.invoice.service.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the client database table.
 */
@Entity
@Table(name="client")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @JsonProperty
    @Column(name="username", nullable = false, unique = true)
    private String userName;

    @JsonIgnore // Prevent password exposure in JSON responses
    @Column(name="password", nullable = false)
    private String password;

    @Lob
    private String address;

    @Lob
    @Column(name="billing_address")
    private String billingAddress;

    @Column(name="business_name")
    private String businessName;

    @Column(name="company_name")
    private String companyName;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="first_name")
    private String firstName;

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

    @Column(name="is_deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted = false;

    @Column(name="created_at", updatable = false)
    private Timestamp createdAt;

    @Column(name="updated_at")
    private Timestamp updatedAt;

    // Many Clients belong to one City
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    // One Client has many Companies
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Company> companies;

    // One Client has many Invoices
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoices;

    // One Client has many Reports
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports;

    // Default Constructor
    public Client() {}

    // Auto-update timestamps
    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    // Getters & Setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
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

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Company> getCompanies() {
        return this.companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Invoice> getInvoices() {
        return this.invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Report> getReports() {
        return this.reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
