package com.amstech.invoice.service.converter.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Category;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.InvoiceItem;
import com.amstech.invoice.service.entity.InvoiceType;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.entity.PaymentMethod;
import com.amstech.invoice.service.entity.PaymentStatus;
import com.amstech.invoice.service.request.model.InvoiceRequest;
import com.amstech.invoice.service.request.model.PaymentRequest;
import com.amstech.invoice.service.request.model.UpdateRequest;
import com.amstech.invoice.service.response.model.PaymentResponseMoodel;

@Component
public class InvoiceModelToEntityConverter {

    public  Invoice getsaveconvertToInvoiceEntity(InvoiceRequest invoiceRequest,Optional <Client> clientOptional,Optional <Company> companyOptional ) {
        Invoice invoice = new Invoice();

        invoice.setClient(clientOptional.get());
        invoice.setCompany(companyOptional.get());
        invoice.setCustomerEmail(invoiceRequest.getCustomerEmail());
        invoice.setCustomerName(invoiceRequest.getCustomerName());
        invoice.setCustomerPhone(invoiceRequest.getCustomerPhone());
        invoice.setTotalAmount(invoiceRequest.getTotalAmount());
        invoice.setSubTotal(invoiceRequest.getSubTotal());
        invoice.setDiscount(invoiceRequest.getDiscount());
        invoice.setTax(invoiceRequest.getTax());
        invoice.setQuantity(invoiceRequest.getQuantity());
        invoice.setCategory(Category.OTHER);    
        invoice.setPaymentStatus(PaymentStatus.PENDING);
        invoice.setPaid(invoiceRequest.getPaid() != null ? invoiceRequest.getPaid() : BigDecimal.ZERO);

        BigDecimal total = invoice.getTotalAmount() != null ? invoice.getTotalAmount() : BigDecimal.ZERO;
        BigDecimal paid = invoice.getPaid() != null ? invoice.getPaid() : BigDecimal.ZERO;
        invoice.setBalance(total.subtract(paid));
    	
        return invoice;
    }

    public static Invoice updateInvoiceModel(Invoice invoice, UpdateRequest updateRequest) {
        
        invoice.setSubTotal(updateRequest.getSubTotal());
        invoice.setDiscount(updateRequest.getDiscount());
        invoice.setNote(updateRequest.getNote());
        invoice.setQuantity(updateRequest.getQuantity());
        invoice.setTotalAmount(updateRequest.getTotalAmount());
        
        return invoice;
    }
    
  }
