package com.amstech.invoice.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // API path specify 
                        .allowedOrigins("http://localhost:4200") // Angular port
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true);
            }
            
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                String pdfPath = System.getProperty("user.home") + "/Desktop/record/";
                registry.addResourceHandler("/files/**")
                        .addResourceLocations("file:" + pdfPath); 
            }
        };
    }
}
