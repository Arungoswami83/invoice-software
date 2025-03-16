package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.InvoiceType;
import lombok.Data;

@Data
public class InvoiceRequest {
	
//	private int id;
	private Integer clientId;
	private Integer companyId;
	private Integer invoiceItemsId;
	private Integer paymentId;
	private Integer invoiceTypeId;
	private String invoiceNumber;
	private Date issueDate;
	private Date dueDate;
	private BigDecimal totalAmount;
	private BigDecimal subTotal;
	private BigDecimal discount;
	private BigDecimal tax;
	private BigDecimal shipping;
	private BigDecimal grandTotal;
	private BigDecimal paid;
	private BigDecimal balance;
	private int quantity;
	private String productCode;
	private String paymentStatus;
	private String PaymentMethod;
	
	
	
}