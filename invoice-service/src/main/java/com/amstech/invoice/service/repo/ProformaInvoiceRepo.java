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

public interface ProformaInvoiceRepo  extends JpaRepository <ProformaInvoice, Integer>{
 
	@Query("SELECT e FROM ProformaInvoice e WHERE e.id = :id AND e.isDeleted = 0")
	Optional<ProformaInvoice> findById(@Param("id") Integer id);

	  @Query("SELECT p FROM ProformaInvoice p WHERE p.isDeleted = 0")
	    Page<ProformaInvoice> findAllActive(Pageable pageable);

	    // Count active proforma invoices
	    @Query("SELECT COUNT(p) FROM ProformaInvoice p WHERE p.isDeleted = 0")
	    long countActiveInvoices();
	}