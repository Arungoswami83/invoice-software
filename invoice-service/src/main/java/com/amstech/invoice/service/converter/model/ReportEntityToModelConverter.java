//package com.amstech.invoice.service.converter.model;
//
//import org.springframework.stereotype.Component;
//
//import com.amstech.invoice.service.entity.Report;
//import com.amstech.invoice.service.response.model.ReportResponseModel;
//
//@Component
//public class ReportEntityToModelConverter {
//	
//	public ReportResponseModel AddResponseInRequest(Report report) {
//		ReportResponseModel reportResponseModel=new ReportResponseModel();
//		
//		reportResponseModel.setId(report.getId());
//		reportResponseModel.setClientId(report.getClient().getId());
//		reportResponseModel.setInvoiceId(report.getInvoice().getId());
//		reportResponseModel.setInvoiceTypeId(report.getInvoiceType().getId());
//		reportResponseModel.setPaymentId(report.getPayment().getId());
//		reportResponseModel.setSalesInvoicesId(report.getSalesInvoices().getId());
//		reportResponseModel.setCreatedAt(report.getCreatedAt());
//		reportResponseModel.setDueDate(report.getDueDate());
//		reportResponseModel.setStatus(report.getStatus());
//		
//		return reportResponseModel;
//	}
//
//}
