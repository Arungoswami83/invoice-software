package com.amstech.invoice.service.converter.entity;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.InvoiceItem;
import com.amstech.invoice.service.entity.InvoiceType;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.entity.PaymentStatus;
import com.amstech.invoice.service.request.model.InvoiceRequest;
import com.amstech.invoice.service.request.model.UpdateRequest;

@Component
public class InvoiceModelToEntityConverter {

    public  Invoice getsaveconvertToInvoiceEntity(InvoiceRequest invoiceRequest,Optional <Client> clientOptional,Optional <Company> companyOptional,Optional <Payment> paymentOptional,Optional <InvoiceItem> invoiceItemOptional,Optional <InvoiceType> invoiceTypeOptional) {
        Invoice invoice = new Invoice();

        invoice.setClient(clientOptional.get());
        invoice.setInvoiceType(invoiceTypeOptional.get());  
        invoice.setCompany(companyOptional.get());
        invoice.setPayment(paymentOptional.get());
        invoice.setInvoiceItem(invoiceItemOptional.get());
        invoice.setIssueDate(invoiceRequest.getIssueDate());
        invoice.setDueDate(invoiceRequest.getDueDate());
        invoice.setTotalAmount(invoiceRequest.getTotalAmount());
        invoice.setSubTotal(invoiceRequest.getSubTotal());
        invoice.setDiscount(invoiceRequest.getDiscount());
        invoice.setTax(invoiceRequest.getTax());
        invoice.setShipping(invoiceRequest.getShipping());
        invoice.setGrandTotal(invoiceRequest.getGrandTotal());
        invoice.setPaid(invoiceRequest.getPaid());
        invoice.setBalance(invoiceRequest.getBalance());
        invoice.setQuantity(invoiceRequest.getQuantity());
        invoice.setProductCode(invoiceRequest.getProductCode());
        invoice.setPaymentStatus(PaymentStatus.PENDING);  
        

        //  Auto-generate Invoice Number if missing
        if (invoiceRequest.getInvoiceNumber() == null || invoiceRequest.getInvoiceNumber().isEmpty()) {
            invoice.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        } else {
            invoice.setInvoiceNumber(invoiceRequest.getInvoiceNumber());
        }

        return invoice;
    }

    public static Invoice updateInvoiceModel(Invoice invoice, UpdateRequest updateRequest) {
        
        invoice.setGrandTotal(updateRequest.getGrandTotal());
        invoice.setSubTotal(updateRequest.getSubTotal());
        invoice.setDiscount(updateRequest.getDiscount());
        invoice.setShipping(updateRequest.getShipping());
        invoice.setPaid(updateRequest.getPaid());
        invoice.setBalance(updateRequest.getBalance());
        invoice.setQuantity(updateRequest.getQuantity());
        invoice.setTotalAmount(updateRequest.getTotalAmount());
        
        return invoice;
    }
    
}
