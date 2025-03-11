//package com.amstech.invoice.service.controller;
//
//import java.util.List;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import com.amstech.invoice.service.entity.GenerateInvoice;
//import com.amstech.invoice.service.entity.Invoice;
//import com.amstech.invoice.service.repo.ClientRepo;
//import com.amstech.invoice.service.repo.GenerateInvoiceRepo;
//import com.amstech.invoice.service.repo.InvoiceRepository;
//import com.amstech.invoice.service.request.model.InvoiceRequest;
//import com.amstech.invoice.service.request.model.generateInvoiceRequest;
//import com.amstech.invoice.service.response.message.ResponseMessage;
//import com.amstech.invoice.service.service.GenerateInvoiceService;
//import com.amstech.invoice.service.service.InvoiceService;
//
//@RestController
//@RequestMapping("generateinvoice")
//public class GenerateInvoiceController {
//	private final Logger LOGGER=LoggerFactory.getLogger(GenerateInvoiceController.class);
//	
//    private InvoiceRepository invoiceRepository;
//	
//	 @Autowired
//	 private GenerateInvoiceService generateInvoiceService;
//	 
//	 @Autowired
//	 private GenerateInvoiceRepo generateInvoiceRepo;
//	 
//	 
//	 public GenerateInvoiceController(GenerateInvoiceService generateInvoiceService) {
//	        this.generateInvoiceService = generateInvoiceService;
//	        LOGGER.info("GenerateInvoiceController: Object Created");
//	 }
//	 
//	 @RequestMapping(method = RequestMethod.POST, value = "/generate", consumes = "application/json", produces = "application/json")
//	    public ResponseMessage generateInvoice(@RequestBody int invoiceId) {
//		 GenerateInvoice generatedInvoice = generateInvoiceService.generateInvoice(invoiceId);
//		    return ResponseMessage.build().withSuccess("generate invoice success",generatedInvoice);
//  
//}
//}
//
////   @RequestMapping(method = RequestMethod.PUT, value = "/update/{invoiceId}", consumes = "application/json", produces = "application/json")
////	 public ResponseMessage UpdateInvoice(@RequestBody generateInvoiceRequest generateInvoiceRequest,@PathVariable int invoiceId){
////       LOGGER.info("Invoice Update with :{}",generateInvoiceRequest.getClientId());
////	   try {
////       Invoice updatedInvoice = generateInvoiceService.updateInvoice(generateInvoiceRequest, invoiceId);
////	   return ResponseMessage.build().withSuccess("Invoice Update successfully",updatedInvoice);
////       } catch (Exception e) {
////         LOGGER.error("Failed to Update user invoice due to: {}", e.getMessage(), e);
////         return ResponseMessage.build().withError(e.getMessage());
////   }       
////   }
//	 
////   @RequestMapping(method = RequestMethod.GET, value = "/findAll", produces = "application/json")
////   public ResponseMessage getAllInvoices() {
////	   LOGGER.info("Fetching all invoices.");
////       return generateInvoiceService.getAllActiveInvoices();
////   }
////   @RequestMapping(method=RequestMethod.DELETE,value=("/softDelete/{id}"),produces="application/json")
////   public ResponseEntity<String>softDeleteInvoice(@PathVariable int id){
////	   try {
////		   String string=generateInvoiceService.softDeleteInvoice(id);
////		   LOGGER.info("soft deleted successfully with ID: {}", id);
////		   return ResponseEntity.ok(string);
////	   }catch(Exception e){
////		   LOGGER.error("Error soft deleting invoice ID:{}", id);
////		   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
////	   }
////   }
////   @RequestMapping(method=RequestMethod.DELETE,value=("/hardDelete/{id}"),produces="application/json")
////   public ResponseEntity<String>hardDelete(@PathVariable int id){
////	   return generateInvoiceService.hardDelete(id);
////   }
////   
////   @RequestMapping(method=RequestMethod.DELETE,value=("/restore/{id}"),produces="application/json")
////   public ResponseEntity<String> restoreInvoice(@PathVariable int id) {
////   	try{
////   		generateInvoiceService.restoreInvoice(id);
////        LOGGER.info("restored successfully with ID: {} ", id);
////       return ResponseEntity.ok("Invoice restored successfully.");
////   }catch (RuntimeException e) {
////	   LOGGER.error("Error while restoring invoice ID: {}", id);
////       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
////   }
////}
//   