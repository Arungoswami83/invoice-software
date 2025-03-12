package com.amstech.invoice.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.invoice.service.entity.ProductInvoice;
import com.amstech.invoice.service.entity.ProformaInvoice;
import com.amstech.invoice.service.entity.RecurringInvoice;
import com.amstech.invoice.service.entity.StandardInvoice;

public interface RecurringInvoiceRepo extends JpaRepository <RecurringInvoice, Integer>{
	
	
	Optional<RecurringInvoice> findById(Integer id);
    @Query("SELECT ri FROM RecurringInvoice ri WHERE ri.isDeleted = 0")
    Page<RecurringInvoice> findAllActive(Pageable pageable);

    // Count active recurring invoices
    @Query("SELECT COUNT(ri) FROM RecurringInvoice ri WHERE ri.isDeleted = 0")
    long countActiveInvoices();
}