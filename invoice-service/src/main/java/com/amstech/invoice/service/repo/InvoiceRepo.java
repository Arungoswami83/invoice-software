
package com.amstech.invoice.service.repo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.amstech.invoice.service.entity.Invoice;
import ch.qos.logback.core.net.server.Client;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {
	
     @Query("SELECT e FROM Invoice e WHERE e.deleted = false") 
	 List<Invoice>findAllInvoice(Pageable pageable);
	      
	 @Query("SELECT e FROM Invoice e WHERE e.id = :id AND e.deleted = false")
	 Optional<Invoice>findById(@Param("id") Integer id);
	 
	 List<Invoice> findByClientId(Integer clientId);
	 
  	 @Query("SELECT COUNT(e) FROM Invoice e WHERE e.deleted = false")  
  	 int countAllInvoice();
  	 
  	 int countByClientId(Integer clientId);
  	 
  	  @Query("SELECT i FROM Invoice i WHERE i.id = :id")
      Invoice findInvoiceById(@Param("id") Integer id);
  	 
  	 @Query("SELECT e FROM Invoice e WHERE e.invoiceNumber = :invoiceNumber AND e.deleted=false")
  	 Optional<Invoice>findByInvoiceNumber(@Param("invoiceNumber")String invoiceNumber);
  
  	 
}

