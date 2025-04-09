package com.amstech.invoice.service.service;

import org.springframework.stereotype.Service;

import com.amstech.invoice.service.entity.ProductInvoice;
import com.amstech.invoice.service.request.model.ProductInvoiceSignupRequestModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ProductInvoicePdfService {
	public String generateProductInvoicePDF(ProductInvoice invoice) {
	    if (invoice == null || invoice.getInvoiceNumber() == null) {
	        throw new IllegalArgumentException("Invoice Number cannot be null");
	    }

	    String pdfFileName = "Product_Invoice_" + invoice.getInvoiceNumber() + ".pdf";
	    String pdfFilePath = "C:/record/" + pdfFileName;

	    try {
	        File file = new File(pdfFilePath);
	        FileOutputStream fos = new FileOutputStream(file);
	        fos.write("This is a dummy PDF content for Product Invoice.".getBytes());
	        fos.close();

	        return pdfFilePath;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}