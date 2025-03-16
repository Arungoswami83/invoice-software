package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;


@Data
public class ClientResponseModel {
	
	private int id;
	private Integer invoiceId;
	private Integer clientId;
	private String firstName;
	private String lastName;
	private String invoiceNumber;
	private Date issueDate;
	private Date dueDate;
	private BigDecimal totalAmount;
	private String status;
	private BigDecimal tax;
	private int quantity;
	private String productCode;
    
}
