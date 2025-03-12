
package com.amstech.invoice.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.amstech.invoice.service")

public class InvoiceManagmentApplicationMain {
	public static void main(String args[]) {
	SpringApplication. run(InvoiceManagmentApplicationMain.class,args);
	System.out.println("InvoiceManagmentApplicationMain  :object created");
	
	}
} 
 
