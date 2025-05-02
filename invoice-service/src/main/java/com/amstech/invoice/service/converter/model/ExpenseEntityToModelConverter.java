package com.amstech.invoice.service.converter.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.Expense;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.response.model.ExpenseResposeModel;
import com.amstech.invoice.service.response.model.PaymentResponseMoodel;

@Component
public class ExpenseEntityToModelConverter {
	
	public ExpenseResposeModel addExRespose(Expense expense) {
		ExpenseResposeModel expenseResposeModel=new ExpenseResposeModel();
		
		expenseResposeModel.setId(expense.getId());
		expenseResposeModel.setAmount(expense.getAmount());
		expenseResposeModel.setDate(expense.getDate());
		expenseResposeModel.setExpenseType(expense.getExpenseType().name());
		expenseResposeModel.setDescription(expense.getDescription());
		
		return expenseResposeModel;
	}
	
	public List<ExpenseResposeModel>convertToResponseModelList(List<Expense> expenses){
	    List<ExpenseResposeModel> responseModel = new ArrayList<>();
	    
	    for (Expense expense: expenses) {
	    	ExpenseResposeModel expenseResposeModel = addExRespose(expense);
	    	responseModel.add(expenseResposeModel);
	    }
	    return responseModel;

	}
}