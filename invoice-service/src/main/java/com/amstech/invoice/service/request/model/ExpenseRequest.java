package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ExpenseRequest {
	private String expenseType;
	private BigDecimal amount;
	private LocalDate date;
	 private String description;
}
