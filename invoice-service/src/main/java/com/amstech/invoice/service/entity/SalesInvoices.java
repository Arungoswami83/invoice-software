package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the sales_invoices database table.
 */
@Entity
@Table(name = "sales_invoices")
@NamedQuery(name = "SalesInvoices.findAll", query = "SELECT s FROM SalesInvoices s")
public class SalesInvoices implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "salesInvoices", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesInvoiceItem> salesInvoiceItems; // ✅ mappedBy अब salesInvoices से match कर रहा है

    @Column(name = "is_deleted")
    private int isDeleted;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "created_at")
    private Timestamp createdAt;

    private BigDecimal discount;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "payment_term")
    private String paymentTerm;

    private BigDecimal price;

    @Lob
    private String signature;

    private String status;

    private BigDecimal subtotal;

    private BigDecimal tax;

    private BigDecimal total;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "salesInvoices", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Analytic> analytics;

    @OneToMany(mappedBy = "salesInvoices")
    @JsonIgnore
    private List<Dashboard> dashboards;

    @OneToMany(mappedBy = "salesInvoices")
    @JsonIgnore
    private List<Report> reports;

    public SalesInvoices() {}

    // Getters and Setters
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Timestamp getCreatedAt() { return this.createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public BigDecimal getDiscount() { return this.discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }

    public String getInvoiceNumber() { return this.invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public String getPaymentTerm() { return this.paymentTerm; }
    public void setPaymentTerm(String paymentTerm) { this.paymentTerm = paymentTerm; }

    public BigDecimal getPrice() { return this.price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getSignature() { return this.signature; }
    public void setSignature(String signature) { this.signature = signature; }

    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getSubtotal() { return this.subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getTax() { return this.tax; }
    public void setTax(BigDecimal tax) { this.tax = tax; }

    public BigDecimal getTotal() { return this.total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public Timestamp getUpdatedAt() { return this.updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public List<Analytic> getAnalytics() { return this.analytics; }
    public void setAnalytics(List<Analytic> analytics) { this.analytics = analytics; }

    public List<Dashboard> getDashboards() { return this.dashboards; }
    public void setDashboards(List<Dashboard> dashboards) { this.dashboards = dashboards; }

    public List<Report> getReports() { return this.reports; }
    public void setReports(List<Report> reports) { this.reports = reports; }

    public List<SalesInvoiceItem> getSalesInvoiceItems() { return salesInvoiceItems; }
    public void setSalesInvoiceItems(List<SalesInvoiceItem> salesInvoiceItems) {
        this.salesInvoiceItems = salesInvoiceItems;
    }

    public int getIsDeleted() { return isDeleted; }
    public void setIsDeleted(int isDeleted) { this.isDeleted = isDeleted; }
}
