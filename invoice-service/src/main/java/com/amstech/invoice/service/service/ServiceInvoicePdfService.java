package com.amstech.invoice.service.service;

import com.amstech.invoice.service.entity.ServiceInvoice;
import com.amstech.invoice.service.request.model.ServiceInvoiceSignupRequestModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class ServiceInvoicePdfService {

	public String generateServiceInvoicePDF(ServiceInvoice invoice) {
	    try {
	        // Define file path (Similar to Recurring Invoice)
	        String fileName = "ServiceInvoice_" + invoice.getInvoiceNumber() + ".pdf";
	        String filePath = "C:/record/" + fileName;  // Same directory as Recurring Invoices

	        // Create PDF (Dummy Content)
	        File file = new File(filePath);
	        FileOutputStream fos = new FileOutputStream(file);
	        fos.write("This is a dummy PDF content for Service Invoice.".getBytes());
	        fos.close();

	        return filePath;  // Return file path
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;  // Return null if PDF generation fails
	    }
	}

}
