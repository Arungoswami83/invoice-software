package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class ClientResponseModel {
	
	private int clientId;
	private String clientFirstName;
	private String clientLastName;
	private String invoiceNumber;
	private long invoiceCount;
	private Date issueDate;
	private Date dueDate;
	private BigDecimal totalAmount;
	private String status;
	private BigDecimal discount;
	private BigDecimal tax;
	private int quantity;
	private String productCode;
	
	

}
