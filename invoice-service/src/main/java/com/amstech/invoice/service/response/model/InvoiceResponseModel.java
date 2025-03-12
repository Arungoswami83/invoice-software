package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data

public class InvoiceResponseModel  {
	
	private int Id;
	private String invoiceNumber;
	private Date issueDate;
	private Date dueDate;
	private BigDecimal totalAmount;
	private String status;
	private BigDecimal subTotal;
	private BigDecimal discount;
	private BigDecimal tax;
	private BigDecimal shipping;
	private BigDecimal grandTotal;
	private BigDecimal paid;
	private BigDecimal balance;
	private int quantity;
	private String productCode;

}



