package com.amstech.invoice.service.repo;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.invoice.service.entity.ProductInvoice;
import com.amstech.invoice.service.entity.StandardInvoice;

public interface ProductInvoiceRepo extends JpaRepository <ProductInvoice, Integer> {

	Optional<ProductInvoice> findById(Integer id);


	@Query("SELECT s FROM ProductInvoice s WHERE s.isDeleted = 1 OR s.isDeleted IS NULL")
	Page<ProductInvoice> findAllActive(Pageable pageable);

	@Query("SELECT COUNT(p) FROM ProductInvoice p")
	long countAllInvoice();

}
