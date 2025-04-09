package com.amstech.invoice.service.service;
import com.amstech.invoice.service.entity.StandardInvoice;
import com.amstech.invoice.service.request.model.StandardInvoiceSignupRequestModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class StandardInvoicePdfService {

	public String generateStandardInvoicePDF(StandardInvoice invoice) {
	    try {
	        // Define file path (Similar to Service Invoice)
	        String fileName = "StandardInvoice_" + invoice.getInvoiceNumber() + ".pdf";
	        String filePath = "C:/record/" + fileName;  // Same directory as Service Invoices

	        // Create PDF (Dummy Content)
	        File file = new File(filePath);
	        FileOutputStream fos = new FileOutputStream(file);
	        fos.write("This is a dummy PDF content for Standard Invoice.".getBytes());
	        fos.close();

	        return filePath;  // Return file path
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;  // Return null if PDF generation fails
	    }
	}
}

