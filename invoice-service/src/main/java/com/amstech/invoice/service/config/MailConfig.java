//package com.amstech.invoice.service.config;
//
//import java.util.Properties;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//@Configuration
//public class MailConfig {
//	
//	@Bean
//	public JavaMailSender getJavaMainSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("rajputprachi435@gmail.com");
//        mailSender.setPort(2061);
//        mailSender.setUsername("rajputprachi435@gmail.com");
//        mailSender.setPassword("Rajput@321Prac#");
//        
//        Properties properties=mailSender.getJavaMailProperties();
//        properties.put("mail.transport.protocol", "smtp");
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.debug", "true");
//         
//        return mailSender;
//	}
//
//}
