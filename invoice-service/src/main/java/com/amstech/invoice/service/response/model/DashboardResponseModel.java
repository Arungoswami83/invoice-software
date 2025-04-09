package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DashboardResponseModel {
	private int id;
	private Integer clientId;
	private BigDecimal totalSales;
    private BigDecimal totalReceivables;
    private BigDecimal totalExpenditure;
    private BigDecimal profit;
    private BigDecimal totalPaymentsReceived;
    private BigDecimal totalRevenue;
}
