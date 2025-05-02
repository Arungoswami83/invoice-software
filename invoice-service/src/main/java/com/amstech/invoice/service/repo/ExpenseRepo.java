package com.amstech.invoice.service.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amstech.invoice.service.entity.Expense;

public interface ExpenseRepo extends JpaRepository<Expense, Integer> {

   @Query("SELECT e FROM Expense e WHERE e.isDeleted = false") 
   List<Expense>findAllExpenses(Pageable pageable);

    @Query("SELECT COUNT(e) FROM Expense e WHERE e.isDeleted = false")  
	 int countAllExpenses();

}

