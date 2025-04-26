package com.amstech.invoice.service.request.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProformaInvoiceSignupRequestModel {

    private String invoiceNumber;
    private String paymentInstructions;
    private String status;
    private BigDecimal totalAmount;
    private String validityPeriod;
    private Integer companyId;
    private Integer clientId;

    @JsonCreator
    public ProformaInvoiceSignupRequestModel(
            @JsonProperty("invoiceNumber") String invoiceNumber,
            @JsonProperty("paymentInstructions") String paymentInstructions,
            @JsonProperty("status") String status,
            @JsonProperty("totalAmount") BigDecimal totalAmount,
            @JsonProperty("validityPeriod") String validityPeriod,
            @JsonProperty("companyId") Integer companyId,
            @JsonProperty("clientId") Integer clientId) {
        this.invoiceNumber = invoiceNumber;
        this.paymentInstructions = paymentInstructions;
        this.status = status;
        this.totalAmount = totalAmount;
        this.validityPeriod = validityPeriod;
        this.companyId = companyId;
        this.clientId = clientId;
    }
}
