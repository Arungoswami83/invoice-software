package com.amstech.invoice.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.amstech.invoice.service.request.model.ExpenseRequest;
import com.amstech.invoice.service.response.ResponseMessage;
import com.amstech.invoice.service.response.model.ExpenseResposeModel;
import com.amstech.invoice.service.response.model.InvoiceResponseModel;
import com.amstech.invoice.service.response.model.PaymentResponseMoodel;
import com.amstech.invoice.service.service.ExpenseService;

@RestController
@RequestMapping("expenses")
public class ExpenseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService expenseService;
    
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
        LOGGER.info("ExpenseController: Object Created");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addExpense", consumes = "application/json", produces = "application/json")    
    public ResponseMessage addExpense(@RequestBody ExpenseRequest expenseRequest) {
        try {
            ExpenseResposeModel expenseResponseModel = expenseService.addExpense(expenseRequest);
            return ResponseMessage.build().withSuccess("Expense added successfully", expenseResponseModel);
        } catch (Exception e) {
            LOGGER.error("Failed to add expense due to: {}", e.getMessage(), e);
            return ResponseMessage.build().withError(e.getMessage());
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/findAll", produces = "application/json")
    public ResponseMessage findAll(@RequestParam("page")Integer page,@RequestParam("size")Integer size) {
        LOGGER.info("Fetching all expenses.");
        try {
            List<ExpenseResposeModel> expenses = expenseService.findAllExpenses(page, size);
            return ResponseMessage.build().withSuccess("Expenses list found successfully", expenses);
        } catch (Exception e) {
            LOGGER.error("Failed to retrieve expenses: {}", e.getMessage(), e);
            return ResponseMessage.build().withError("Failed to retrieve expenses: " + e.getMessage());
        }
    }
}

