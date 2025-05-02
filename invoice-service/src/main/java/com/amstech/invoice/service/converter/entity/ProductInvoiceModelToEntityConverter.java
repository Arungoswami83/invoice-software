package com.amstech.invoice.service.converter.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.ProductInvoice;
import com.amstech.invoice.service.request.model.ProductInvoiceSignupRequestModel;
import com.amstech.invoice.service.request.model.ProductInvoiceUpdateRequestModel;

@Component
public class ProductInvoiceModelToEntityConverter {
    
    // Signup Converter: Converts from DTO to Entity for creating a new invoice
    public ProductInvoice getSignupConverter(ProductInvoiceSignupRequestModel dto) {
        
        ProductInvoice entity = new ProductInvoice();
        
        // Setting the values from DTO to Entity
        entity.setId(dto.getId());
        entity.setPaymentMethod(dto.getPaymentMethod());
        entity.setDueDate(dto.getDueDate());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : 0); // Default to 0 if null
        entity.setInvoiceNumber(dto.getInvoiceNumber());
        entity.setPdfPath(dto.getPdfPath());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setStatus(dto.getStatus());
        entity.setSubTotal(dto.getSubTotal());
        entity.setDiscount(dto.getDiscount());
        entity.setTax(dto.getTax());
        entity.setGrandTotal(dto.getGrandTotal());
        entity.setPaid(dto.getPaid());
        entity.setBalance(dto.getBalance());
        entity.setQuantity(dto.getQuantity());
        entity.setCustomerEmail(dto.getCustomerEmail());
        entity.setCustomerName(dto.getCustomerName());
        entity.setCustomerPhone(dto.getCustomerPhone());
        entity.setCategory(dto.getCategory());
        entity.setNote(dto.getNote());
        entity.setPaymentStatus(dto.getPaymentStatus());
        entity.setAccountDetails(dto.getAccountDetails());
        entity.setBuyerDetails(dto.getBuyerDetails());
        entity.setHandlingCosts(dto.getHandlingCosts());
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setPaymentTerm(dto.getPaymentTerm());
        entity.setShipping(dto.getShipping());
        entity.setSupplier(dto.getSupplier());
        entity.setTaxCalculation(dto.getTaxCalculation());
        entity.setTotalPayable(dto.getTotalPayable());
        entity.setClient(entity.getClient());
        entity.setCompany(entity.getCompany());
        // If invoice number is empty or null, generate a default one
        if (dto.getInvoiceNumber() == null || dto.getInvoiceNumber().isEmpty()) {
            entity.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        } else {
            entity.setInvoiceNumber(dto.getInvoiceNumber());
        }

        return entity;
    }
    
    // Update Converter: Converts from Update DTO to Entity for updating an existing invoice
    public ProductInvoice getUpdateConverter(ProductInvoiceUpdateRequestModel productInvoiceUpdateRequestModel, ProductInvoice productInvoice) {
        
        // Setting values from the Update Request Model to the existing ProductInvoice entity
        productInvoice.setId(productInvoiceUpdateRequestModel.getId());
        productInvoice.setAccountDetails(productInvoiceUpdateRequestModel.getAccountDetails());
        productInvoice.setBuyerDetails(productInvoiceUpdateRequestModel.getBuyerDetails());
        
        // Converting long timestamp to Date for due date
        productInvoice.setDueDate(new Date(productInvoiceUpdateRequestModel.getDueDate()));
        
        productInvoice.setSupplier(productInvoiceUpdateRequestModel.getSupplier());
        productInvoice.setOrderNumber(productInvoiceUpdateRequestModel.getOrderNumber());
        productInvoice.setPaymentMethod(productInvoiceUpdateRequestModel.getPaymentMethod());
        productInvoice.setPaymentTerm(productInvoiceUpdateRequestModel.getPaymentTerm());
        productInvoice.setShipping(productInvoiceUpdateRequestModel.getShipping());
        productInvoice.setTaxCalculation(productInvoiceUpdateRequestModel.getTaxCalculation());
        productInvoice.setTotalPayable(productInvoiceUpdateRequestModel.getTotalPayable());
        
        return productInvoice;
    }
}
