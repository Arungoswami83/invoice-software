package com.amstech.invoice.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenAPIConfiguration {
	
    @Bean
    public OpenAPI defineOpenApi() {
        Info information = new Info()
                .title("Invoice Service")
                .version("1.0")
                .description("This is a demo project for invoice service.");

        return new OpenAPI().info(information);
    }
}
