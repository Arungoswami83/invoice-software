package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;

public class ProformaInvoiceResponseModel {
	
	private int id;
    private String paymentInstructions;
    private String status;
    private BigDecimal totalAmount;
	private String validityPeriod;

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPaymentInstructions() {
		return paymentInstructions;
	}
	public void setPaymentInstructions(String paymentInstructions) {
		this.paymentInstructions = paymentInstructions;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getValidityPeriod() {
		return validityPeriod;
	}
	public void setValidityPeriod(String validityPeriod) {
		this.validityPeriod = validityPeriod;
	}


}

