package com.amstech.invoice.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.invoice.service.entity.ProductInvoice;
import com.amstech.invoice.service.entity.RecurringInvoice;
import com.amstech.invoice.service.entity.ServiceInvoice;
import com.amstech.invoice.service.entity.StandardInvoice;

public interface ServiceInvoiceRepo extends JpaRepository <ServiceInvoice, Integer> {

	@Query("SELECT e FROM ServiceInvoice e WHERE e.id = :id AND e.isDeleted = 0")
	Optional<ServiceInvoice> findById(@Param("id") Integer id);
	
	@Query("SELECT p FROM ServiceInvoice p WHERE p.isDeleted = 0")
	Page<ServiceInvoice> findAllActive(Pageable pageable);

    // Count active invoices
    @Query("SELECT COUNT(p) FROM ServiceInvoice p WHERE p.isDeleted = 0")
    long countActiveInvoices();
    
}