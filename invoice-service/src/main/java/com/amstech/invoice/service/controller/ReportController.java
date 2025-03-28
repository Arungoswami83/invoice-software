//package com.amstech.invoice.service.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.amstech.invoice.service.request.model.InvoiceRequest;
//import com.amstech.invoice.service.request.model.ReportRequestModel;
//import com.amstech.invoice.service.response.ResponseMessage;
//import com.amstech.invoice.service.response.model.InvoiceResponseModel;
//import com.amstech.invoice.service.response.model.ReportResponseModel;
//import com.amstech.invoice.service.service.ReportService;
//
//@RestController
//@RequestMapping("/report")
//public class ReportController {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
//
//	@Autowired
//	private ReportService reportService;
//
//	public ReportController(ReportService reportService) {
//		this.reportService = reportService;
//		LOGGER.info("ReportService : Object Created");
//	}
//
//	@RequestMapping(method = RequestMethod.POST, value = "/create", consumes = "application/json", produces = "application/json")
//	public ResponseMessage createReport(@RequestBody ReportRequestModel reportRequestModel) {
//		LOGGER.info("Report created with  ID: {}", reportRequestModel.getClientId());
//		try {
//			ReportResponseModel responseModel = reportService.AddReport(reportRequestModel);
//			return ResponseMessage.build().withSuccess("Report Created Successfully", responseModel);
//		} catch (Exception e) {
//			LOGGER.error("Failed to Create user Report due to: {}", e.getMessage(), e);
//			return ResponseMessage.build().withError(e.getMessage());
//		}
//	}
//
//}