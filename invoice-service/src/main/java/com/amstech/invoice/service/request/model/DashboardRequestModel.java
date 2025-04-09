package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import org.eclipse.angus.mail.handlers.text_html;

import lombok.Data;

@Data
public class DashboardRequestModel {

	private Integer clientId;
	private BigDecimal totalSales;
    private BigDecimal totalReceivables;
    private BigDecimal totalExpenditure;
    private BigDecimal profit;
    private BigDecimal totalPaymentsReceived;
    private LocalDateTime createdAt;
    private BigDecimal totalRevenue;
    
}
