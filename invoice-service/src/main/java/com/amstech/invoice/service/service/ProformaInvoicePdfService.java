package com.amstech.invoice.service.service;
import com.amstech.invoice.service.entity.ProformaInvoice;
import com.amstech.invoice.service.request.model.ProformaInvoiceSignupRequestModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

@Service
public class ProformaInvoicePdfService {
	 public String generateProformaInvoicePDF(ProformaInvoice invoice) {
	        try {
	            // Define file path
	            String fileName = "ProformaInvoice_" + invoice.getInvoiceNumber() + ".pdf";
	            String filePath = "C:/record/" + fileName;  // Change to a valid directory

	            // Create PDF (Dummy Content)
	            File file = new File(filePath);
	            FileOutputStream fos = new FileOutputStream(file);
	            fos.write("This is a dummy PDF content.".getBytes());
	            fos.close();

	            return filePath;  // Return file path
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;  // Return null if PDF generation fails
	        }
	    }

	    }