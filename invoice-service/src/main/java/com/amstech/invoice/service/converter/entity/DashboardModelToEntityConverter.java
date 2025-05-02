package com.amstech.invoice.service.converter.entity;

import java.util.Optional;
import org.springframework.stereotype.Component;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Dashboard;
import com.amstech.invoice.service.request.model.DashboardRequestModel;
import com.amstech.invoice.service.request.model.MonthlyDataRequestModel;

@Component
public class DashboardModelToEntityConverter {

	public Dashboard convertToEntity(DashboardRequestModel dashboardRequestModel,Optional<Client>optionalClient) {
        if (dashboardRequestModel == null) {
            return null;
        }
        Dashboard dashboard=new Dashboard();
        dashboard.setClient(optionalClient.get());
        dashboard.setProfit(dashboardRequestModel.getProfit());
        dashboard.setTotalExpenditure(dashboardRequestModel.getTotalExpenditure());
        dashboard.setTotalPaymentsReceived(dashboardRequestModel.getTotalPaymentsReceived());
        dashboard.setTotalReceivables(dashboardRequestModel.getTotalReceivables());
        dashboard.setTotalSales(dashboardRequestModel.getTotalSales());
        dashboard.setCreatedAt(dashboardRequestModel.getCreatedAt());
        dashboard.setTotalRevenue(dashboardRequestModel.getTotalRevenue());
        
        return dashboard;
	}

	public Dashboard AddChart(MonthlyDataRequestModel RequestModel,Optional<Client>optionalClient) {
		Dashboard dashboard=new Dashboard();
		dashboard.setClient(optionalClient.get());
		
		return dashboard;
	}
	
	
}
