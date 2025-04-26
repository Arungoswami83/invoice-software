package com.amstech.invoice.service.service.interfac;

import java.util.List;

import com.amstech.invoice.service.request.model.DashboardRequestModel;
import com.amstech.invoice.service.request.model.MonthlyDataRequestModel;
import com.amstech.invoice.service.response.model.DashboardResponseModel;
import com.amstech.invoice.service.response.model.MonthlySalesExpenseResponseModel;

public interface DashboardServices {
    DashboardResponseModel DashboardSummary(DashboardRequestModel dashboardRequestModel);
    
	List<MonthlySalesExpenseResponseModel> getMonthlyChartData(MonthlyDataRequestModel requestModel);



}
