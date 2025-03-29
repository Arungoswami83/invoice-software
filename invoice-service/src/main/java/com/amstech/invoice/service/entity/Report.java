package com.amstech.invoice.service.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reports")
@NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
public class Report implements Serializable {
    private static final long serialVersionUID = 1L;
   
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "issue_date", nullable = false)
        @Temporal(TemporalType.DATE)
        private Date issueDate;

        @Column(name = "due_date")
        @Temporal(TemporalType.DATE)
        private Date dueDate;

        @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        @Temporal(TemporalType.TIMESTAMP)
        private Date createdAt;

        @Enumerated(EnumType.STRING)
        @Column(name = "status", nullable = false)
        private ReportStatus status;

        @ManyToOne
        @JoinColumn(name = "client_id", nullable = false)
        private Client client;

        @ManyToOne
        @JoinColumn(name = "invoice_id", nullable = false)
        private Invoice invoice;

        @Column(name = "remarks")
        private String remarks;

        @ManyToOne
        @JoinColumn(name = "sales_invoices_id")
        private SalesInvoices salesInvoices;

        @ManyToOne
        @JoinColumn(name = "payment_id")
        private Payment payment;

        @ManyToOne
        @JoinColumn(name = "invoice_types_id")
        private InvoiceType invoiceType;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Date getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(Date issueDate) {
            this.issueDate = issueDate;
        }

        public Date getDueDate() {
            return dueDate;
        }

        public void setDueDate(Date dueDate) {
            this.dueDate = dueDate;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public ReportStatus getStatus() {
            return status;
        }

        public void setStatus(ReportStatus status) {
            this.status = status;
        }

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        public Invoice getInvoice() {
            return invoice;
        }

        public void setInvoice(Invoice invoice) {
            this.invoice = invoice;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public SalesInvoices getSalesInvoices() {
			return salesInvoices;
		}

		public void setSalesInvoices(SalesInvoices salesInvoices) {
			this.salesInvoices = salesInvoices;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Payment getPayment() {
            return payment;
        }

        public void setPayment(Payment payment) {
            this.payment = payment;
        }

        public InvoiceType getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(InvoiceType invoiceType) {
            this.invoiceType = invoiceType;
        }
    }


