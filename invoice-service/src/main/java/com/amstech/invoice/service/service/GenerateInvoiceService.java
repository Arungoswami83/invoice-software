//package com.amstech.invoice.service.service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.amstech.invoice.service.converter.entity.GenerateInvoioceConverter;
//import com.amstech.invoice.service.converter.entity.InvoiceModelToEntityConverter;
//import com.amstech.invoice.service.converter.model.InvoiceEntityToModelConverter;
//import com.amstech.invoice.service.entity.Client;
//import com.amstech.invoice.service.entity.Company;
//import com.amstech.invoice.service.entity.GenerateInvoice;
//import com.amstech.invoice.service.entity.Invoice;
//import com.amstech.invoice.service.entity.InvoiceItem;
//import com.amstech.invoice.service.entity.InvoiceType;
//import com.amstech.invoice.service.entity.Payment;
//import com.amstech.invoice.service.repo.ClientRepo;
//import com.amstech.invoice.service.repo.GenerateInvoiceRepo;
//import com.amstech.invoice.service.repo.InvoiceRepository;
//import com.amstech.invoice.service.request.model.InvoiceRequest;
//import com.amstech.invoice.service.request.model.generateInvoiceRequest;
//import com.amstech.invoice.service.response.message.ResponseMessage;
//
//@Service
//public class GenerateInvoiceService{
//	private final Logger LOGGER=LoggerFactory.getLogger(GenerateInvoiceService.class);
//	
//	@Autowired
//	private ClientRepo clientRepo;
//
//	@Autowired
//	private InvoiceRepository invoiceRepository;
//	
//	@Autowired
//	private GenerateInvoiceRepo generateInvoiceRepo;
//	
//	 public GenerateInvoiceService(InvoiceRepository invoiceRepository) {
//	        this.invoiceRepository=invoiceRepository;
//	    }
//	
//	 public GenerateInvoice generateInvoice(int invoiceId) {
//	       Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new RuntimeException("Invoice not found"));
//
//	        Client client = invoice.getClient();
//	        if (client == null) {
//	            throw new RuntimeException("Client not associated with Invoice");
//	        }
//
//	        GenerateInvoice generateInvoice = new GenerateInvoice();
//	        generateInvoice.setInvoice(invoice);
//	        generateInvoice.setClient(client);
//
//	        return generateInvoiceRepo.save(generateInvoice);
//	    }
//}
//
//	 
////	 public List<GenerateInvoice> getAllActiveInvoices() {
////		    return generateInvoiceRepo.findAllActiveInvoices();
////		}
//
//
////	public GenerateInvoice updateInvoice(generateInvoiceRequest generateInvoiceRequest, int invoiceId) {
////	    LOGGER.info("Updating invoice with ID: {}", invoiceId);
////
////	    GenerateInvoice existingInvoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + invoiceId));
////
////	    Client client = clientRepo.findById(generateInvoiceRequest.getClientId()).orElseThrow(() -> new RuntimeException("Client not found. Cannot generate invoice."));
////
////	    existingInvoice = GenerateInvoioceConverter.UpdateConerter(generateInvoiceRequest,existingInvoice);
////	    existingInvoice.setClient(client);
////
////	    return invoiceRepository.save(existingInvoice);
////	}
//
////	
////	  public List<generateInvoiceRequest>AllInvoices(Integer page,Integer size)throws Exception  {
////		  List<GenerateInvoice>generateInvoices=invoiceRepository.findAllGenaerateInvoice(PageRequest.of(page, size));
////			return invoiceEntityToModelConverter.findAll(generateInvoices);
////
////	    }
////	public InvoiceRequest findById(int id)  throws Exception {
////		Optional<Invoice>invoiceoptional=invoiceRepository.findById(id);
////		if (invoiceoptional.isEmpty()) {
////			throw new Exception("The user accunt does not exist.");
////		}
////		Invoice invoice=invoiceoptional.get();
////		if (invoice.getDeleted()) {
////            throw new Exception("Your account has been deactivated. Please contact the administrator for assistance.");
////		}
////	    return invoiceEntityToModelConverter.convertToModel(invoice);
////}
//
////	  //SoftDelete
////	  public String softDeleteInvoice(int id) {
////		  Optional<Invoice>optionalInvoice=invoiceRepository.findById(id);
////		  if (optionalInvoice.isPresent()) {
////			  Invoice invoice= optionalInvoice.get();
////			  invoice.setDeleted(true);
////			  invoiceRepository.save(invoice);
////			  return "Invoice deleted successfully!";
////		}else {
////			throw new RuntimeException("Invoice Not Found");
////		}
////	  }
////		//HardDelete
////		public ResponseEntity<String>hardDelete(int id) {
////			Optional<Invoice>optionalInvoice=invoiceRepository.findById(id);
////			  if (optionalInvoice.isPresent()) {
////				invoiceRepository.deleteById(id);  //Permanent removes from DB
////				return ResponseEntity.ok("Invoice deleted permanently!");
////			}else {
////			    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice Not Found");
////			}
////	  }	
////		 public void restoreInvoice(int id) {
////			 Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
////			 if (optionalInvoice.isPresent()) {
////		            Invoice invoice = optionalInvoice.get();
////		            invoice.setDeleted(false);
////		            invoiceRepository.save(invoice);
////		            LOGGER.info("Invoice restored successfully with ID: {}", id);
////		        } else {
////		            throw new RuntimeException("Invoice not found");
////		        }
////		    }
////		 
//	
