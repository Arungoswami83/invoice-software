package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.ServiceDetail;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
@Data
public class ServiceInvoiceSignupRequestModel {
	private int id;
    private Date dueDate;
    private BigDecimal grandTotal;
    private String invoiceNumber;
    private String notes;
    private String paymentTerm;
    private String status;
    private BigDecimal subTotal;
    private BigDecimal tax;
	private Integer clientId;
	


	
	

}
