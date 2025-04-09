package com.amstech.invoice.service.service;

import com.amstech.invoice.service.entity.RecurringInvoice;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Service
public class RecurringInvoicePdfService {

    // ✅ Desktop path ke sath folder
    private static final String PDF_DIRECTORY = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "record";

    public String generateInvoicePDF(RecurringInvoice invoice) {
        if (invoice == null || invoice.getInvoiceNumber() == null) {
            throw new IllegalArgumentException("Invoice or Invoice Number cannot be null");
        }

        String pdfFileName = "ProformaInvoice_" + invoice.getInvoiceNumber() + ".pdf";
        String pdfFilePath = PDF_DIRECTORY + File.separator + pdfFileName;

        try {
            // ✅ Folder create if not exists
            File dir = new File(PDF_DIRECTORY);
            if (!dir.exists()) {
                boolean created = dir.mkdirs();
                if (created) {
                    System.out.println("✅ Folder created: " + PDF_DIRECTORY);
                } else {
                    System.out.println("❌ Failed to create folder: " + PDF_DIRECTORY);
                }
            }

            // ✅ PDF Create
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Proforma Invoice", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            // ✅ Invoice Fields
            document.add(new Paragraph("Invoice Number: " + invoice.getInvoiceNumber()));
            document.add(new Paragraph("Client: " + invoice.getClient().getFirstName() + " " + invoice.getClient().getLastName()));
            document.add(new Paragraph("Auto Payment Setup: " + (invoice.getAutoPaymentSetup() == 1 ? "Yes" : "No")));
            document.add(new Paragraph("End Date: " + (invoice.getEndDate() != null ? sdf.format(invoice.getEndDate()) : "N/A")));
            document.add(new Paragraph("Payment Term: " + invoice.getPaymentTerm()));
            document.add(new Paragraph("Total Payable: ₹" + invoice.getTotalPayable()));

            document.close();
            System.out.println("✅ PDF generated at: " + pdfFilePath);
            return pdfFilePath;

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
