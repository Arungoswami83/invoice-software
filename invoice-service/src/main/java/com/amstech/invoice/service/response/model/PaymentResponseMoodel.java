package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.entity.PaymentMethod;

import lombok.Data;

@Data
public class PaymentResponseMoodel {
	
	private int id;
	private Integer invoiceId;
	private BigDecimal amountPaid;
	private String paymentDate;
	private String notes;
	private String paymentMethod;
	private String paymentStatus;

}
