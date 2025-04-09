package com.amstech.invoice.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.invoice.service.entity.Dashboard;

public interface DashboardRepo extends JpaRepository<Dashboard,Integer>{
	
    Optional<Dashboard> findFirstByClientIdOrderByCreatedAtDesc(Integer clientId);
    
    @Query("SELECT FUNCTION('MONTH', d.createdAt) as month, " +
    	       "SUM(d.totalSales) as totalSales, SUM(d.totalExpenditure) as totalExpenditure " +
    	       "FROM Dashboard d WHERE d.client.id = :clientId " +
    	       "GROUP BY FUNCTION('MONTH', d.createdAt) ORDER BY month ASC")
    	List<Object[]> getMonthlySalesAndExpenses(@Param("clientId") Integer clientId);
}