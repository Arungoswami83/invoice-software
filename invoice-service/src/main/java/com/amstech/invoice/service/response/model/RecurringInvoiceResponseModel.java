package com.amstech.invoice.service.response.model;

public class RecurringInvoiceResponseModel {
            private int id;
	
	     private byte autoPaymentSetup;
	 
	    private String paymentTerm;
	    public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public byte getAutoPaymentSetup() {
			return autoPaymentSetup;
		}
		public void setAutoPaymentSetup(byte autoPaymentSetup) {
			this.autoPaymentSetup = autoPaymentSetup;
		}
	
		public String getPaymentTerm() {
			return paymentTerm;
		}
		public void setPaymentTerm(String paymentTerm) {
			this.paymentTerm = paymentTerm;
		}
	    

	

}
