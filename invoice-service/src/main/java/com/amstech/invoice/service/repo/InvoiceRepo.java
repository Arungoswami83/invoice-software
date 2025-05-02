
package com.amstech.invoice.service.repo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.PaymentStatus;

import ch.qos.logback.core.net.server.Client;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {
	
	@Query("SELECT i FROM Invoice i")
	Page<Invoice> findAllInvoice(Pageable pageable);
	 
	 @Query("SELECT i FROM Invoice i WHERE i.client.id = :clientId")
	 List<Invoice> findByClientId(@Param("clientId")Integer clientId);
	 
  	 @Query("SELECT COUNT(e) FROM Invoice e WHERE e.deleted = false")  
  	 int countAllInvoice();
  	 
  	 int countByClientId(Integer clientId);
  	 
  	 Optional<Invoice> findTopByClientIdOrderByCreatedAtDesc(Integer clientId);
  	 
  	  @Query("SELECT i FROM Invoice i WHERE i.id = :id")
      Invoice findInvoiceById(@Param("id") Integer id);
  	 
      Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
      
      Page<Invoice> findByCustomerNameContainingIgnoreCase(String customerName, Pageable pageable);

      Page<Invoice> findByPaymentStatus(PaymentStatus paymentStatus, Pageable pageable);

      Page<Invoice> findByCustomerNameContainingIgnoreCaseAndPaymentStatus(String customerName, PaymentStatus paymentStatus, Pageable pageable);

	 
}

