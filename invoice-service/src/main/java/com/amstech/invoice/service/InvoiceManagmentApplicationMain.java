package com.amstech.invoice.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.amstech.invoice.service")
public class InvoiceManagmentApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(InvoiceManagmentApplicationMain.class, args);
        System.out.println("object : created");
    }
}
