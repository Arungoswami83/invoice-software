package com.amstech.invoice.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.invoice.service.entity.State;

public interface StateRepo extends JpaRepository<State, Integer>{
	
	 @Query("SELECT s.country.name FROM State s WHERE s.id = :stateId")
	    String findCountryByStateId(@Param("stateId") Integer stateId);
	 
	    @Query("SELECT s.country.name FROM State s WHERE s.name = :stateName")
	    String findCountryByStateName(@Param("stateName") String stateName);
	    
	    @Query("SELECT s FROM State s WHERE s.country.id = :countryId")
	    List<State> findStatesByCountryId(@Param("countryId") Integer countryId);

}

