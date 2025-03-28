package com.amstech.invoice.service.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.amstech.invoice.service.entity.Dashboard;
import com.amstech.invoice.service.request.model.DashboardRequestModel;
import com.amstech.invoice.service.request.model.ExpenseRequest;
import com.amstech.invoice.service.request.model.MonthlyDataRequestModel;
import com.amstech.invoice.service.response.ResponseMessage;
import com.amstech.invoice.service.response.model.DashboardResponseModel;
import com.amstech.invoice.service.response.model.ExpenseResposeModel;import com.amstech.invoice.service.response.model.MonthlySalesExpenseResponseModel;
import com.amstech.invoice.service.service.DashboardServiceImpl;
import com.amstech.invoice.service.service.interfac.DashboardServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("dashboard")
public class DashboardController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private DashboardServices dashboardServices;
    
    @Autowired
    private DashboardServiceImpl dashboardServiceImpl;
    
    public DashboardController(DashboardServices dashboardServices) {
    	this.dashboardServices=dashboardServices;
    	LOGGER.info("DashboardService: Object Created");
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/summary", produces = "application/json")
    public ResponseMessage getDashboardSummary(@RequestBody @Valid DashboardRequestModel dashboardRequestModel) {
        LOGGER.info("Fetching dashboard summary for client ID: {}", dashboardRequestModel.getClientId());

        try {
            DashboardResponseModel dashboardResponseModel = dashboardServices.DashboardSummary(dashboardRequestModel);
            return ResponseMessage.build().withSuccess("Dashboard fetched successfully", dashboardResponseModel);
        } catch (Exception e) {
            LOGGER.error("Failed to fetch dashboard due to: {}", e.getMessage(), e);
            return ResponseMessage.build().withError(e.getMessage());
        }
    }
   
    @RequestMapping(method = RequestMethod.POST, value = "/monthly-chart", produces = "application/json")
    public ResponseMessage getMonthlySalesAndExpenses(@RequestBody @Valid MonthlyDataRequestModel requestModel) {
        LOGGER.info("Fetching monthly sales & expenses for client ID: {}", requestModel.getClientId());
        try {
            List<MonthlySalesExpenseResponseModel> chartData = dashboardServices.getMonthlyChartData(requestModel);

            if (chartData.isEmpty()) {
                LOGGER.warn("No chart data found for Client ID: {}", requestModel.getClientId());
                return ResponseMessage.build().withSuccess("No data available", chartData);
            }

            LOGGER.info("Chart data fetched successfully for Client ID: {}", requestModel.getClientId());
            return ResponseMessage.build().withSuccess("Chart data fetched successfully", chartData);

        } catch (Exception e) {
            LOGGER.error("Failed to fetch dashboard due to: {}", e.getMessage(), e);
            return ResponseMessage.build().withError("Failed to fetch chart data: " + e.getMessage());
        }
    }
	
    @RequestMapping(method = RequestMethod.GET, value = "/searchBy", produces = "application/json")
    public ResponseMessage searchDashboardData(
            @RequestParam(value = "type", required = false) String type, 
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "clientId", required = false) Integer clientId,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(value = "minSales", required = false) BigDecimal minSales,
            @RequestParam(value = "maxSales", required = false) BigDecimal maxSales,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "gender", required = false) String gender) {

        LOGGER.info("Received request to search dashboard data with filters.");

        try {
            List<DashboardResponseModel> result = dashboardServiceImpl.searchDashboardData(type, page, size, clientId, startDate, endDate, minSales, maxSales, status, gender);
            long totalRecord = dashboardServiceImpl.countDashboardData(type, clientId, startDate, endDate, minSales, maxSales, status, gender);

            return ResponseMessage.build().withSuccess("Dashboard List found successfully").withData(result).withTotalRecords(totalRecord);
        } catch (Exception e) {
            LOGGER.error("Failed to find dashboard list due to: {}", e.getMessage(), e);
            return ResponseMessage.build().withError(e.getMessage());
        }
    }

}