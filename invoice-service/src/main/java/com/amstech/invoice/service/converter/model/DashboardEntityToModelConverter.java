package com.amstech.invoice.service.converter.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;
import com.amstech.invoice.service.entity.Dashboard;
import com.amstech.invoice.service.entity.Expense;
import com.amstech.invoice.service.response.model.DashboardResponseModel;
import com.amstech.invoice.service.response.model.ExpenseResposeModel;
import com.amstech.invoice.service.response.model.MonthlySalesExpenseResponseModel;

@Component
public class DashboardEntityToModelConverter {
	
	public DashboardResponseModel addDashboardResponse(Dashboard dashboard) {
		DashboardResponseModel dashboardResponseModel=new DashboardResponseModel();
		
		dashboardResponseModel.setClientId(dashboard.getClient().getId());
		dashboardResponseModel.setProfit(dashboard.getProfit());
		dashboardResponseModel.setId(dashboard.getId());
		dashboardResponseModel.setTotalExpenditure(dashboard.getTotalExpenditure());
		dashboardResponseModel.setTotalPaymentsReceived(dashboard.getTotalPaymentsReceived());;
		dashboardResponseModel.setTotalReceivables(dashboard.getTotalReceivables());
		dashboardResponseModel.setTotalSales(dashboard.getTotalSales());
		dashboardResponseModel.setTotalRevenue(dashboard.getTotalRevenue());
		
		return dashboardResponseModel;
	}
	
	public MonthlySalesExpenseResponseModel addChartResponse(Dashboard dashboard) {
	    MonthlySalesExpenseResponseModel expenseResponseModel = new MonthlySalesExpenseResponseModel();

	    if (dashboard.getCreatedAt() != null) {
	        LocalDateTime createdAt = dashboard.getCreatedAt();
	        expenseResponseModel.setMonth(createdAt.getMonthValue());
	    } else {
	        expenseResponseModel.setMonth(0);
	    }
	    expenseResponseModel.setTotalExpenditure(dashboard.getTotalExpenditure());
	    expenseResponseModel.setTotalSales(dashboard.getTotalSales());

	    return expenseResponseModel;
	}
	
	public List<DashboardResponseModel>getFindAllConvert(List<Dashboard>dashboardList){
		List<DashboardResponseModel>dashboardResponseModels=new ArrayList<>();
		
		for(Dashboard dashboard :dashboardList) {
			DashboardResponseModel responseModel=new DashboardResponseModel();
			responseModel.setId(dashboard.getId());
			responseModel.setClientId(dashboard.getClient().getId());
			responseModel.setProfit(dashboard.getProfit());
			responseModel.setTotalExpenditure(dashboard.getTotalExpenditure());
			responseModel.setTotalPaymentsReceived(dashboard.getTotalPaymentsReceived());;
			responseModel.setTotalReceivables(dashboard.getTotalReceivables());
			responseModel.setTotalRevenue(dashboard.getTotalRevenue());
			responseModel.setTotalSales(dashboard.getTotalSales());
		}
		return dashboardResponseModels;
	}
	
	
	
	
	
}