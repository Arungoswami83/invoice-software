package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.InvoiceType;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.entity.ReportStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
public class ReportRequestModel {
	
	private Date issueDate;
	private Date dueDate;
	private Date createdAt;
	private Integer invoiceTypeId;
	private Integer clientId;
	private Integer invoiceId;
	private Integer paymentId;
	private Integer salesInvoicesId;
	private ReportStatus status;
	private String remarks;


}
