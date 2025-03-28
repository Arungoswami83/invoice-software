package com.amstech.invoice.service.converter.entity;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.InvoiceType;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.entity.Report;
import com.amstech.invoice.service.entity.SalesInvoices;
import com.amstech.invoice.service.request.model.ReportRequestModel;

@Component
public class ReportModelToEntityConverter {

	public Report getAddReport(ReportRequestModel requestModel,Optional<Client>optionalClient,Optional<Invoice>optionalInvoice,Optional<Payment>optionalPayment,Optional<InvoiceType>optionalInvoiceType,Optional<SalesInvoices>optionalSalesInvoices) {
		Report report=new Report();
		
		report.setClient(optionalClient.get());
		report.setInvoice(optionalInvoice.get());
		report.setInvoiceType(optionalInvoiceType.get());
		report.setPayment(optionalPayment.get());
		report.setSalesInvoices(optionalSalesInvoices.get());
		report.setCreatedAt(requestModel.getCreatedAt());
		report.setDueDate(requestModel.getDueDate());
		report.setRemarks(requestModel.getRemarks());
		report.setStatus(requestModel.getStatus());
		
		return report;
	}
	
	
}
