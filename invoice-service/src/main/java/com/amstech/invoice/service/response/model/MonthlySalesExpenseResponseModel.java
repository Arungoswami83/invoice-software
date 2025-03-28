package com.amstech.invoice.service.response.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class MonthlySalesExpenseResponseModel {
	 	private int month;
	    private BigDecimal totalSales;
	    private BigDecimal totalExpenditure;
	
}
