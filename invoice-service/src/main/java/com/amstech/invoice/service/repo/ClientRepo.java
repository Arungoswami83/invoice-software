package com.amstech.invoice.service.repo;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.SalesInvoices;
@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {

	    Optional<Client> findById(int id) ;
			
	    @Query("SELECT c FROM Client c WHERE c.city.id = :cityId")
	    List<Client> findByCityId(@Param("cityId") Integer cityId);

	    
	    Optional<Client> findBymobileNumber(String mobileNumber);

	   
	    @Query("SELECT c FROM Client c WHERE c.email = :email")
	    Optional<Client> findByEmail(@Param("email") String email);
	   
	    List<Client> findByCompanyNameContainingIgnoreCase(String companyName);

	    
//	    List<Client> findByCity(String city);
	  @Query("SELECT a FROM Client a WHERE a.email = :userName OR  a.mobileNumber =: userName AND a.password = :password")
	    Optional<Client> findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

	    
	    Optional<Client> findByFirstName(String firstName);
	    
	    @Query("SELECT c FROM Client c WHERE c.isDeleted = false")
	    List<Client> findAllClient(Pageable pageable);
		    
		 
		 @Query("SELECT COUNT(c) FROM Client c WHERE c.isDeleted = false")
		 long countAllClient();

	 
		 @Transactional
		 @Modifying
		 @Query("UPDATE Client c SET c.isDeleted = false WHERE c.id = :id") 
		 void restoreClient(@Param("id") Integer id);

}


