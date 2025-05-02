package com.amstech.invoice.service.converter.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Expense;
import com.amstech.invoice.service.entity.ExpenseType;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.entity.PaymentMethod;
import com.amstech.invoice.service.entity.PaymentStatus;
import com.amstech.invoice.service.request.model.ExpenseRequest;
import com.amstech.invoice.service.request.model.PaymentRequest;

@Component
public class ExpenseModelToEntityConverter {
	
	public static Expense addExpense(ExpenseRequest expenseRequest) {
		Expense expense=new Expense();
		expense.setAmount(expenseRequest.getAmount());
		expense.setDate(expenseRequest.getDate());
		expense.setDescription(expenseRequest.getDescription());
		expense.setExpenseType(ExpenseType.valueOf(expenseRequest.getExpenseType().toUpperCase()));
		
		return expense;
	}
}