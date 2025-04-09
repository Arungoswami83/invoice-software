package com.amstech.invoice.service.repo.custom;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.amstech.invoice.service.entity.Dashboard;

public interface DashboardCustomRepo{
	
	List<Dashboard> searchDashboardData(String type, Integer page, Integer size, Integer clientId,LocalDateTime startDate, LocalDateTime endDate, BigDecimal minSales, BigDecimal maxSales, String status,String gender);
	public long countBy(String type, Integer clientId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal minSales,BigDecimal maxSales, String status, String gender);
	
}
