package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.InvoiceType;
import lombok.Data;

@Data
public class InvoiceRequest {
	
	private Integer clientId;
	private Integer companyId;
	private BigDecimal totalAmount;
	private BigDecimal subTotal;
	private BigDecimal discount;
	private BigDecimal tax;
	private BigDecimal grandTotal;
	private int quantity;
	private String customerEmail;
	private String customerPhone;
	private String customerName;
	private BigDecimal paid;
	private BigDecimal balance;
	private String category; // Service, Product, Other
    private String productCode;
	private String paymentStatus;
    private String paymentMethod;

	
	
	
}