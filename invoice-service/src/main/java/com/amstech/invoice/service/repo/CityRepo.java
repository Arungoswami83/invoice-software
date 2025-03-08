package com.amstech.invoice.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.invoice.service.entity.City;
import com.amstech.invoice.service.entity.Client;

public interface CityRepo extends JpaRepository<City, Integer> { 

	
	 @Query("SELECT c.state.name FROM City c WHERE c.id = :cityId")
	    String findStateByCityId(@Param("cityId") Integer cityId);
	
	    @Query("SELECT c.state.name FROM City c WHERE c.name = :cityName")
	    String findStateByCityName(@Param("cityName") String cityName);

	    
	    @Query("SELECT c FROM City c WHERE c.state.id = :stateId")
	    List<City> findCitiesByStateId(@Param("stateId") Integer stateId);

		
	
	}


