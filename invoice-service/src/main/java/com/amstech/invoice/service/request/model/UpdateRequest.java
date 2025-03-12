package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class UpdateRequest {

	private int id;
	private BigDecimal totalAmount; 
	private String status;
	private BigDecimal subTotal;
	private BigDecimal discount;
	private BigDecimal shipping;
	private BigDecimal grandTotal;
	private BigDecimal paid;
	private BigDecimal balance;
	private int quantity;

}
