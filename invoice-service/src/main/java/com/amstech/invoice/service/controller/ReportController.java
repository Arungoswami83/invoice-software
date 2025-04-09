package com.amstech.invoice.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.amstech.invoice.service.request.model.ReportRequestModel;
import com.amstech.invoice.service.response.ResponseMessage;
import com.amstech.invoice.service.response.model.ReportResponseModel;
import com.amstech.invoice.service.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
        LOGGER.info("ReportController : Object Created");
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseMessage createReport(@RequestBody ReportRequestModel reportRequestModel) {
        LOGGER.info("Creating report for Invoice ID: {}", reportRequestModel.getInvoiceId());
        try {
            ReportResponseModel responseModel = reportService.AddReport(reportRequestModel);
            return ResponseMessage.build().withSuccess("Report Created Successfully", responseModel);
        } catch (Exception e) {
            LOGGER.error("Failed to create report due to: {}", e.getMessage(), e);
            return ResponseMessage.build().withError(e.getMessage());
        }
    }
}
