package com.amstech.invoice.service.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CORSFilter implements Filter {

    	
    		public CORSFilter() {
    			System.out.println("CORSFilter: Object created..");
    		}

    		@Override
    		public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
    				throws IOException, ServletException {
    			HttpServletResponse response = (HttpServletResponse) res;
    			HttpServletRequest request = (HttpServletRequest) req;

    			// CORS Headers
    			response.setHeader("Access-Control-Allow-Origin", "*"); 
    			response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
    			response.setHeader("Access-Control-Max-Age", "3600");
    			response.setHeader("Access-Control-Allow-Headers","token , *");

    			// Pre-flight Requests Handle
    			if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
    				response.setStatus(HttpServletResponse.SC_OK);
    				return;
    			}

    			chain.doFilter(req, res);
    		}

    		@Override
    		public void init(FilterConfig filterConfig) {
    		}

    		@Override
    		public void destroy() {
    		}
    	
//        return new WebMvcConfigurer() {
//
//            
//
//            @Override
//            public void addResourceHandlers(ResourceHandlerRegistry registry) {
//                String pdfPath = System.getProperty("user.home") + "/Desktop/record/";
//                System.out.println("PDF Path for static access: " + pdfPath);
//
//                registry.addResourceHandler("/files/**")
//                        .addResourceLocations("file:" + pdfPath.replace("\\", "/"));
//            }
//        };
}


