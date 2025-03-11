package com.amstech.invoice.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfiguration {
	
 @Bean
 public OpenAPI defineOpenApi() {
	Info information=new Info().title("Invoice Service").version("1.0").description("This is a demo project to develop a invoice managment system");
     return new OpenAPI().info(information);
 }
 
	
	
}
