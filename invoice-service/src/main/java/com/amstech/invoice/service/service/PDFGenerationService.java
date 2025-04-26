package com.amstech.invoice.service.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import com.amstech.invoice.service.entity.Invoice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PDFGenerationService {

    private static final String PDF_DIRECTORY = System.getProperty("user.home") + "\\Desktop\\record";

    public String generateInvoicePDF(Invoice invoice) {
        if (invoice == null || invoice.getInvoiceNumber() == null) {
            throw new IllegalArgumentException("Invoice or Invoice Number cannot be null");
        }

        // Create directory if it doesn't exist
        File dir = new File(PDF_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String pdfFileName = "Invoice_" + invoice.getInvoiceNumber() + ".pdf";
        String pdfFilePath = PDF_DIRECTORY + "\\" + pdfFileName;

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
            document.open();

            // Title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Invoice", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Invoice Details
            document.add(new Paragraph("Invoice Number: " + invoice.getInvoiceNumber()));
            document.add(new Paragraph("Client: " + invoice.getClient().getFirstName() + " " + invoice.getClient().getLastName()));
            document.add(new Paragraph("Issue Date: " + invoice.getCreatedAt()));
            document.add(new Paragraph("Due Date: " + invoice.getDueDate()));
            document.add(new Paragraph("Quantity: " + invoice.getQuantity()));
            document.add(new Paragraph("Tax: " + invoice.getTax()));
            document.add(new Paragraph("Payment Status: " + invoice.getPaymentStatus()));
            document.add(new Paragraph("Company Name: " + invoice.getCompany()));
            document.add(new Paragraph("Total Amount: $" + invoice.getTotalAmount()));
            document.add(new Paragraph("\n"));

            document.close();

            System.out.println("✅ PDF generated at: " + pdfFilePath);

            return "http://localhost:2061/client/files/" + pdfFileName;

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
