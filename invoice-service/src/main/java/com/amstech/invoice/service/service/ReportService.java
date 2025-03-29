package com.amstech.invoice.service.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amstech.invoice.service.converter.entity.ReportModelToEntityConverter;
import com.amstech.invoice.service.converter.model.ReportEntityToModelConverter;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.InvoiceType;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.entity.Report;
import com.amstech.invoice.service.entity.SalesInvoices;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.repo.InvoiceRepo;
import com.amstech.invoice.service.repo.InvoiceTypeRepo;
import com.amstech.invoice.service.repo.PaymentRepo;
import com.amstech.invoice.service.repo.ReportRepo;
import com.amstech.invoice.service.repo.SalesInvoicesRepo;
import com.amstech.invoice.service.request.model.ReportRequestModel;
import com.amstech.invoice.service.response.model.ReportResponseModel;

@Service
public class ReportService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    private ReportRepo reportRepo;

    @Autowired
    private InvoiceRepo invoiceRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private InvoiceTypeRepo invoiceTypeRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private SalesInvoicesRepo salesInvoicesRepo;

    @Autowired
    private ReportEntityToModelConverter entityToModelConverter;

    @Autowired
    private ReportModelToEntityConverter reportModelToEntityConverter;

    public ReportResponseModel AddReport(ReportRequestModel reportRequestModel) throws Exception {
        Optional<Client> optionalClient = clientRepo.findById(reportRequestModel.getClientId());
        if (optionalClient.isEmpty()) {
            LOGGER.error("Client doesn't exist for ID: {}", reportRequestModel.getClientId());
            throw new Exception("Client does not exist");
        }

        Optional<InvoiceType> invoiceTypesOptional = invoiceTypeRepo.findById(reportRequestModel.getInvoiceTypeId());
        if (invoiceTypesOptional.isEmpty()) {
            LOGGER.error("InvoiceType doesn't exist for ID: {}", reportRequestModel.getInvoiceTypeId());
            throw new Exception("InvoiceType does not exist");
        }

        Optional<Invoice> optionalInvoice = invoiceRepo.findById(reportRequestModel.getInvoiceId());
        if (optionalInvoice.isEmpty()) {
            LOGGER.error("Invoice doesn't exist for ID: {}", reportRequestModel.getInvoiceId());
            throw new Exception("Invoice does not exist");
        }

        Optional<SalesInvoices> optionalSales = salesInvoicesRepo.findById(reportRequestModel.getSalesInvoicesId());
        if (optionalSales.isEmpty()) {
            LOGGER.error("SalesInvoice doesn't exist for ID: {}", reportRequestModel.getSalesInvoicesId());
            throw new Exception("SalesInvoice does not exist");
        }

        Optional<Payment> optionalPayment = paymentRepo.findById(reportRequestModel.getPaymentId());
        if (optionalPayment.isEmpty()) {
            LOGGER.error("Payment doesn't exist for ID: {}", reportRequestModel.getPaymentId());
            throw new Exception("Payment does not exist");
        }

        Report report = reportModelToEntityConverter.getAddReport(
            reportRequestModel, optionalClient, optionalInvoice, optionalPayment, invoiceTypesOptional, optionalSales
        );

        Report savedReport = reportRepo.save(report);
        LOGGER.info("Saving Report with ID: {}", savedReport.getId());

        return entityToModelConverter.AddResponseInRequest(savedReport);
    }
}
