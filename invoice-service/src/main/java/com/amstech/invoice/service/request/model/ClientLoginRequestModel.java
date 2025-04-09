package com.amstech.invoice.service.request.model;

import jakarta.annotation.sql.DataSourceDefinition;
import lombok.Data;

@Data
public class ClientLoginRequestModel {
	
	private String username;
	private String password;
	
	
	
	

}
