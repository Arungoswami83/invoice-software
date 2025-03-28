package com.amstech.invoice.service.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amstech.invoice.service.converter.entity.DashboardModelToEntityConverter;
import com.amstech.invoice.service.converter.entity.ExpenseModelToEntityConverter;
import com.amstech.invoice.service.converter.model.DashboardEntityToModelConverter;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.Dashboard;
import com.amstech.invoice.service.entity.Expense;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.repo.DashboardRepo;
import com.amstech.invoice.service.repo.InvoiceRepo;
import com.amstech.invoice.service.repo.custom.DashboardCustomRepo;
import com.amstech.invoice.service.request.model.DashboardRequestModel;
import com.amstech.invoice.service.request.model.ExpenseRequest;
import com.amstech.invoice.service.request.model.MonthlyDataRequestModel;
import com.amstech.invoice.service.response.model.DashboardResponseModel;
import com.amstech.invoice.service.response.model.ExpenseResposeModel;
import com.amstech.invoice.service.response.model.InvoiceResponseModel;
import com.amstech.invoice.service.response.model.MonthlySalesExpenseResponseModel;
import com.amstech.invoice.service.service.interfac.DashboardServices;

@Service
public class DashboardServiceImpl implements DashboardServices{

	private static final Logger LOGGER=LoggerFactory.getLogger(DashboardServiceImpl.class);
	
	@Autowired
	private DashboardRepo dashboardRepo;
	
	@Autowired
	private InvoiceRepo invoiceRepo;
	
	@Autowired
	private ClientRepo clientRepo;
	
	@Autowired
	private DashboardModelToEntityConverter dashboardModelToEntityConverter;
	
	@Autowired
	private DashboardEntityToModelConverter dashboardEntityToModelConverter;
	
	@Autowired
//	@Qualifier("DashboardCustomImplRepo")
	private DashboardCustomRepo dashboardCustomRepo;
	
	
	@Override
	public DashboardResponseModel DashboardSummary(DashboardRequestModel dashboardRequestModel) {
	    Optional<Client> clientOptional = clientRepo.findById(dashboardRequestModel.getClientId());

	    if (!clientOptional.isPresent()) {
	        LOGGER.error("Client doesn't exist for ID: {}", dashboardRequestModel.getClientId());
	        throw new RuntimeException("Client does not exist");
	    }
	    Optional<Dashboard> optionalDashboard = dashboardRepo.findFirstByClientIdOrderByCreatedAtDesc(dashboardRequestModel.getClientId());
	    Dashboard dashboard;
	    if (optionalDashboard.isPresent()) {
	        dashboard = optionalDashboard.get();
	    } else {
	        LOGGER.warn("No existing dashboard data found for Client ID: {}, creating a new record.", dashboardRequestModel.getClientId());
	        dashboard = dashboardModelToEntityConverter.convertToEntity(dashboardRequestModel, clientOptional);
	        dashboard = dashboardRepo.save(dashboard);
	        }
	    return dashboardEntityToModelConverter.addDashboardResponse(dashboard);
	}
	
	@Override
	public List<MonthlySalesExpenseResponseModel> getMonthlyChartData(MonthlyDataRequestModel requestModel) {
	    LOGGER.info("Fetching monthly sales & expenses for Client ID: {}", requestModel.getClientId());

	    Optional<Client> clientOptional = clientRepo.findById(requestModel.getClientId());
	    if (!clientOptional.isPresent()) {
	        LOGGER.error("Client doesn't exist for ID: {}", requestModel.getClientId());
	        throw new RuntimeException("Client does not exist");
	    }
	    Optional<Dashboard> clientDashboard = dashboardRepo.findFirstByClientIdOrderByCreatedAtDesc(requestModel.getClientId());
	    if (!clientDashboard.isPresent()) {
	        LOGGER.warn("No dashboard data found for Client ID: {}", requestModel.getClientId());
	        return List.of();
	    }
	    List<Object[]> rawChartData = dashboardRepo.getMonthlySalesAndExpenses(requestModel.getClientId());
	    if (rawChartData.isEmpty()) {
	        LOGGER.warn("No chart data found for Client ID: {}", requestModel.getClientId());
	        return List.of();
	    }
	    // Convert raw data to response models
	    List<MonthlySalesExpenseResponseModel> responseList = new ArrayList<>();
	    for (Object[] row : rawChartData) {
	        MonthlySalesExpenseResponseModel model = new MonthlySalesExpenseResponseModel();
	        model.setMonth(((Number) row[0]).intValue()); 
	        model.setTotalSales((BigDecimal) row[1]); 
	        model.setTotalExpenditure((BigDecimal) row[2]);   
	        responseList.add(model);
	    }

	    return responseList;
	}
	
	public List<DashboardResponseModel> searchDashboardData(String type, Integer page, Integer size, Integer clientId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal minSales, BigDecimal maxSales, String status, String gender) {
		LOGGER.info("Fetching dashboard data with filters: type={}, page={}, size={}, clientId={}, startDate={}, endDate={}, minSales={}, maxSales={}, status={}, gender={}", type, page, size, clientId, startDate, endDate, minSales, maxSales, status, gender);
		List<Dashboard>dashboardList=dashboardCustomRepo.searchDashboardData(type, page, size, clientId, startDate, endDate, minSales, maxSales, status, gender);
		return dashboardEntityToModelConverter.getFindAllConvert(dashboardList);
}
	
	
	public long countDashboardData(String type, Integer clientId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal minSales, BigDecimal maxSales, String status, String gender) {
		LOGGER.info("Counting dashboard data with filters: type={}, clientId={}, startDate={}, endDate={}, minSales={}, maxSales={}, status={}, gender={}", type, clientId, startDate, endDate, minSales, maxSales, status, gender);

		return dashboardCustomRepo.countBy(type, clientId, startDate, endDate, minSales, maxSales, status, gender);
}	
	
}