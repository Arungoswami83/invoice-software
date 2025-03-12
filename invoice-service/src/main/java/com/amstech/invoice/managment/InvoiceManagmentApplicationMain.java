package com.amstech.invoice.managment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.amstech.invoice.managment")

public class InvoiceManagmentApplicationMain {
	public static void main(String args[]) {
	SpringApplication. run(InvoiceManagmentApplicationMain.class,args);
	System.out.println("InvoiceManagmentApplicationMain  :object created");
	
	}
} 
 