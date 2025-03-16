//package com.amstech.invoice.service.service;
//
//import java.io.File;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//
//@Service
//public class EmailService {
//
//	@Autowired
//    private JavaMailSender mailSender;
//	
//	public void sendSimpleEmail(String toEmail,String subject,String body) {
//		SimpleMailMessage mailMessage= new SimpleMailMessage();
//		mailMessage.setTo(toEmail);
//		mailMessage.setSubject(subject);
//		mailMessage.setText(body);
//        mailSender.send(mailMessage);
//        System.out.println("Email Sent Successfully!");
//	}
//    public void sendInvoiceEmail(String toEmail, String subject, String body, String pdfPath) throws MessagingException {
//    	try {
//    	MimeMessage mimeMessage=mailSender.createMimeMessage();
//    	MimeMessageHelper helper=new MimeMessageHelper(mimeMessage, true);
//    	
//    	 helper.setTo(toEmail);
//         helper.setSubject(subject);
//         helper.setText(body);
//         
//         FileSystemResource file = new FileSystemResource(new File(pdfPath));
//    	helper.addAttachment("Invoice.pdf", file);
//    	
//    	mailSender.send(mimeMessage);
//    	System.out.println("Invoice with PDF Sent Successfully!");
//    }catch (MessagingException e) {
//        e.printStackTrace();
//        System.out.println("Failed to send invoice email!");
//    }
//}    
//    
//}   
//    
//    
//    
//    
//    
//    
