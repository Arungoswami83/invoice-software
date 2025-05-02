package com.amstech.invoice.service.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.amstech.invoice.service.converter.entity.ExpenseModelToEntityConverter;
import com.amstech.invoice.service.converter.model.ExpenseEntityToModelConverter;
import com.amstech.invoice.service.entity.Expense;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.repo.ExpenseRepo;
import com.amstech.invoice.service.request.model.ExpenseRequest;
import com.amstech.invoice.service.response.model.ExpenseResposeModel;
import com.amstech.invoice.service.response.model.InvoiceResponseModel;
import com.amstech.invoice.service.response.model.PaymentResponseMoodel;

@Service
public class ExpenseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseService.class);

    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private ExpenseEntityToModelConverter expenseEntityToModelConverter;

    public ExpenseResposeModel addExpense(ExpenseRequest expenseRequest) {
        Expense expense = ExpenseModelToEntityConverter.addExpense(expenseRequest);
        Expense savedExpense = expenseRepo.save(expense);
        LOGGER.info("Saving Expense with ID: {}", savedExpense.getId());

        return expenseEntityToModelConverter.addExRespose(savedExpense);
    }
    public List<ExpenseResposeModel> findAllExpenses(Integer page, Integer size) { 
        List<Expense> expenses = expenseRepo.findAllExpenses(PageRequest.of(page, size));
        
        if (expenses.isEmpty()) {
            LOGGER.warn("No expenses found.");  
        }
        return expenseEntityToModelConverter.convertToResponseModelList(expenses);
    }
	public long countAllExpenses() throws Exception {
        return expenseRepo.countAllExpenses();
    }

    
}
