package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class UpdateRequest {

	private int id;
	private BigDecimal totalAmount; 
	private String note;
	private BigDecimal subTotal;
	private BigDecimal discount;
	private int quantity;

}
