package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.sql.Date;

import com.google.protobuf.Timestamp;

public class ServiceInvoiceResponseModel {
	 private int id;
	    private String invoiceNumber;
	   
	    private BigDecimal grandTotal;
	    private String notes;
	    private String paymentTerm;
	    private String status;
	    private BigDecimal subTotal;
	    private BigDecimal tax;
	    private int IsDeleted;
	  
		public int getIsDeleted() {
			return IsDeleted;
		}
		public void setIsDeleted(int isDeleted) {
			IsDeleted = isDeleted;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getInvoiceNumber() {
			return invoiceNumber;
		}
		public void setInvoiceNumber(String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}
	
		public BigDecimal getGrandTotal() {
			return grandTotal;
		}
		public void setGrandTotal(BigDecimal grandTotal) {
			this.grandTotal = grandTotal;
		}
		public String getNotes() {
			return notes;
		}
		public void setNotes(String notes) {
			this.notes = notes;
		}
		public String getPaymentTerm() {
			return paymentTerm;
		}
		public void setPaymentTerm(String paymentTerm) {
			this.paymentTerm = paymentTerm;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public BigDecimal getSubTotal() {
			return subTotal;
		}
		public void setSubTotal(BigDecimal subTotal) {
			this.subTotal = subTotal;
		}
		public BigDecimal getTax() {
			return tax;
		}
		public void setTax(BigDecimal tax) {
			this.tax = tax;
		}
	

}
