package com.amstech.invoice.service.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentMethod {
    CASH, 
    CARD, 
    UPI, 
    BANK_TRANSFER, 
    CHEQUE;

	public static PaymentMethod fromString(String value) {
	    for (PaymentMethod method : PaymentMethod.values()) {
	        if (method.name().equalsIgnoreCase(value.trim())) { // trim() spaces remove karega
	            return method;
	        }
	    }
	    throw new IllegalArgumentException("Invalid payment method: " + value);
	}
}

