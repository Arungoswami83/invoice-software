package com.amstech.invoice.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.invoice.service.entity.ProductInvoice;
import com.amstech.invoice.service.entity.ServiceInvoice;
import com.amstech.invoice.service.entity.StandardInvoice;

public interface StandardInvoiceRepo extends JpaRepository <StandardInvoice, Integer>{
	@Query("SELECT e FROM StandardInvoice e WHERE e.id = :id AND e.isDeleted = 0")
	Optional<StandardInvoice> findById(@Param("id") Integer id);
	
	@Query("SELECT s FROM StandardInvoice s WHERE s.isDeleted = 0 OR s.isDeleted IS NULL")
	Page<StandardInvoice> findAllActive(Pageable pageable);

    @Query("SELECT COUNT(p) FROM StandardInvoice p WHERE p.isDeleted = 0")
    long countAllInvoice();
}