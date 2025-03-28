package com.amstech.invoice.service.response.model;


import java.math.BigDecimal;
import java.util.Date;
import com.amstech.invoice.service.entity.InvoiceStatus;
import lombok.Data;

@Data
public class ReportResponseModel {

	private int id;
	private Integer clientId;
	private Integer invoiceId;
	private Integer invoiceTypeId;
	private Integer salesInvoicesId;
	private Integer paymentId;
	private Date dueDate;
	private Date createdAt;
	private InvoiceStatus status;
	
}
