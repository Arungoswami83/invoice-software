package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ExpenseResposeModel {
	
	private int id;
	private String expenseType;
	private BigDecimal amount;
	private LocalDate date;
	private String description;
}
