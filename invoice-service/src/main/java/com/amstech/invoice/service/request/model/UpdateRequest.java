package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class UpdateRequest {

	private int id;
	private BigDecimal grandTotal;
	private String customerEmail;
	private String customerPhone;
	private String customerName;
	private BigDecimal totalAmount; 
	private String note;
	private BigDecimal subTotal;
	private BigDecimal discount;
	private int quantity;
	private BigDecimal tax;
	private BigDecimal balance;

}
