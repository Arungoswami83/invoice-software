package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;


@Data
public class ClientResponseModel {
	
	private int id;
	private Integer clientId;
	private String firstName;
	private String lastName;
	private String gender;
	private LocalDateTime createdAt;
	private Date dueDate;
	private BigDecimal totalAmount;
	private String status;
	private BigDecimal tax;
	private int quantity;
	private String productCode;
    
}
