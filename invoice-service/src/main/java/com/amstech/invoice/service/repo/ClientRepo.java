package com.amstech.invoice.service.repo;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.SalesInvoices;
@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {

	    Optional<Client> findById(int id) ;
			
	    @Query("SELECT c FROM Client c WHERE c.city.id = :cityId")
	    List<Client> findByCityId(@Param("cityId") Integer cityId);

	    
	    Optional<Client> findByPhoneNumber(String phoneNumber);

	   
	    @Query("SELECT c FROM Client c WHERE c.email = :email")
	    Optional<Client> findByEmail(@Param("email") String email);
	   
	    List<Client> findByCompanyNameContainingIgnoreCase(String companyName);

	    
//	    List<Client> findByCity(String city);
//	  @Query("SELECT a FROM Client a WHERE a.userName = :userName AND a.password = :password")
	    Optional<Client> findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

	    
	    Optional<Client> findByFirstName(String firstName);

	    @Query("SELECT COUNT(c) FROM Client c")  
	    long countAllClient();

		
		 @Query("SELECT c FROM Client c") 
		    List<Client> findAllClient();
	 
	}


