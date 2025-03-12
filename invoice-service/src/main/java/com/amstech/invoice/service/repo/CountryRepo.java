package com.amstech.invoice.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.invoice.service.entity.Country;

public interface CountryRepo extends JpaRepository<Country, Integer> {

	
	 @Query("SELECT s.name FROM State s WHERE s.country.id = :countryId")
	    List<String> findStatesByCountryId(@Param("countryId") Integer countryId);
	
	    @Query("SELECT s.name FROM State s WHERE s.country.name = :countryName")
	    List<String> findStatesByCountryName(@Param("countryName") String countryName);
	    
}
