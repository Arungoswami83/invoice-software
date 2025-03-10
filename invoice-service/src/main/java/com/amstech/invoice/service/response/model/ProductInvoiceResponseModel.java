package com.amstech.invoice.service.response.model;

import java.sql.Date;

import lombok.Data;
@Data
public class ProductInvoiceResponseModel {
	private int id;
	private String accountDetails;
	private String buyerDetails;

	private String supplier;
	
	


}
