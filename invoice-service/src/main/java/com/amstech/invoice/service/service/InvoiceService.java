package com.amstech.invoice.service.service;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.amstech.invoice.service.converter.entity.InvoiceModelToEntityConverter;
import com.amstech.invoice.service.converter.model.InvoiceEntityToModelConverter;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.InvoiceItem;
import com.amstech.invoice.service.entity.InvoiceType;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.entity.PaymentMethod;
import com.amstech.invoice.service.entity.PaymentStatus;
import com.amstech.invoice.service.entity.Report;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.repo.CompanyRepo;
import com.amstech.invoice.service.repo.InvoiceItemRepo;
import com.amstech.invoice.service.repo.InvoiceRepo;
import com.amstech.invoice.service.repo.InvoiceTypeRepo;
import com.amstech.invoice.service.repo.PaymentRepo;
import com.amstech.invoice.service.repo.ReportRepo;
import com.amstech.invoice.service.request.model.InvoiceRequest;
import com.amstech.invoice.service.request.model.UpdateRequest;
import com.amstech.invoice.service.response.model.ClientResponseModel;
import com.amstech.invoice.service.response.model.InvoiceResponseModel;
import com.amstech.invoice.service.response.model.PaymentResponseMoodel;


@Service
public class InvoiceService {
	private final Logger LOGGER=LoggerFactory.getLogger(InvoiceService.class);
	
	@Autowired
	private InvoiceRepo invoiceRepo;
	
	@Autowired
	private ClientRepo clientRepo;
 
	 @Autowired
	 private CompanyRepo companyRepo;
	 
	 @Autowired
	 private PaymentRepo paymentRepo;
	 
	 @Autowired
	 private InvoiceEntityToModelConverter invoiceEntityToModelConverter;
	 
	 @Autowired
	 private InvoiceModelToEntityConverter invoiceModelToEntityConverter;
	 
	 @Autowired
	 private PDFGenerationService pdfGenerationService;
	 
	 public InvoiceResponseModel createInvoice(InvoiceRequest invoiceRequest) throws Exception {
		    //  Check if Client exists
		    Optional<Client> clientOptional = clientRepo.findById(invoiceRequest.getClientId());
		    if (!clientOptional.isPresent()) {
		        LOGGER.error("Client doesn't exist for ID: {}", invoiceRequest.getClientId());
		        throw new Exception("Client does not exist");
		    }

		    //  Check if Company exists
		    Optional<Company> companyOptional = companyRepo.findById(invoiceRequest.getCompanyId());
		    if (!companyOptional.isPresent()) {
		        LOGGER.error("Company doesn't exist for ID: {}", invoiceRequest.getCompanyId());
		        throw new Exception("Company does not exist");
		    }
	 				    

		    //  Convert Request to Entity and Set Invoice Number
		   Invoice invoice = invoiceModelToEntityConverter.getsaveconvertToInvoiceEntity(invoiceRequest, clientOptional, companyOptional);
		    invoice.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

		    //  Save Invoice
		    Invoice savedInvoice = invoiceRepo.save(invoice);
		    LOGGER.info("Saving Invoice with ID: {}", savedInvoice.getId());

		    //  Create & Save Payment
		    Payment payment = new Payment();
		    payment.setInvoice(savedInvoice);
		    payment.setAmountPaid(invoiceRequest.getPaid() != null ? invoiceRequest.getPaid() : BigDecimal.ZERO);
 		    payment.setPaymentDate(LocalDate.now());

		    payment.setPaymentMethod(getPaymentMethodSafe(invoiceRequest.getPaymentMethod()));

		    // Set Payment Status and Timestamps
		    payment.setPaymentStatus(PaymentStatus.PAID);
		    payment.setCreatedAt(LocalDateTime.now());
		    payment.setUpdatedAt(LocalDateTime.now());
		    paymentRepo.save(payment); 

		    // Update Invoice with Paid and Balance Amount
		    savedInvoice.setPaid(payment.getAmountPaid());
		    savedInvoice.setBalance(savedInvoice.getSubTotal().subtract(payment.getAmountPaid()));
 
		    // Generate PDF and Save Path
		    String pdfPath = pdfGenerationService.generateInvoicePDF(savedInvoice);
		    savedInvoice.setPdfPath(pdfPath);
		    invoiceRepo.save(savedInvoice);

		    //  Convert to Response Model
		    InvoiceResponseModel responseModel = invoiceEntityToModelConverter.convertEntityToModel(savedInvoice);
		    responseModel.setPdfUrl(savedInvoice.getPdfPath());
		    LOGGER.info("Invoice Created with PDF URL: {}", responseModel.getPdfUrl());

		    return responseModel;
		}
	 
	 private PaymentMethod getPaymentMethodSafe(String method) throws Exception {
		    if (method == null) return PaymentMethod.CASH;

		    switch (method.trim().toUpperCase()) {
		        case "CASH": return PaymentMethod.CASH;
		        case "UPI": return PaymentMethod.UPI;
		        case "CARD": return PaymentMethod.CARD;
		        case "BANK_TRANSFER": return PaymentMethod.BANK_TRANSFER;
		        case "CHEQUE": return PaymentMethod.CHEQUE;
		        default: throw new Exception("Invalid payment method: " + method);
		    }
		}

	 
	 public InvoiceResponseModel updateInvoice(UpdateRequest updateRequest) throws Exception { 
		    Optional<Invoice> optionalInvoice = invoiceRepo.findById(updateRequest.getId());

		    if (optionalInvoice.isEmpty()) {
		        throw new Exception("Invoice does not exist");
		    }
		    Invoice invoice = optionalInvoice.get();
		     invoice = invoiceModelToEntityConverter.getupdateInvoiceModel(invoice, updateRequest);
		     invoiceRepo.save(invoice);

		    return invoiceEntityToModelConverter.getfindById(invoice);
		}
	
		public InvoiceResponseModel findById(Integer id)  throws Exception {
		Optional<Invoice>invoiceoptional=invoiceRepo.findById(id);
		if (invoiceoptional.isEmpty()) {
			throw new Exception("The user accunt does not exist.");
		}
		Invoice invoice=invoiceoptional.get();
		if (invoice.getDeleted()) {
            throw new Exception("Your account has been deactivated. Please contact the administrator for assistance.");
		}
	    return invoiceEntityToModelConverter.getfindById(invoice);
	}
		
		public InvoiceResponseModel findByInvoiceNumber(String invoiceNumber)  throws Exception {
			Optional<Invoice>invoiceoptional=invoiceRepo.findByInvoiceNumber(invoiceNumber);
			if (invoiceoptional.isEmpty()) {
				throw new Exception("The user account does not exist.");
			}
			Invoice invoice=invoiceoptional.get();
		    return invoiceEntityToModelConverter.getfindByInvoiceNumber(invoice);
		}
			
		public Map<String, Object> allInvoices(Integer page, Integer size) throws Exception {

			List<Invoice> invoices = invoiceRepo.findAll(PageRequest.of(page, size)).getContent();
		    System.out.println("Invoices fetched: " + invoices.size());
			List<InvoiceResponseModel> invoiceResponseModels = invoiceEntityToModelConverter.findAll(invoices);
		    System.out.println("InvoiceResponseModels created: " + invoiceResponseModels.size());

		    long totalRecord = invoiceRepo.count(); // Or your custom countAllInvoice()

		    Map<String, Object> response = new HashMap<>();
		    response.put("data", invoiceResponseModels);
		    response.put("totalRecords", totalRecord);
		    response.put("pageNumber", page);
		    response.put("pageSize", size);

		    return response;
		}

	
		public long countAllInvoice() throws Exception {
	        return invoiceRepo.countAllInvoice();
	    }
		
		public Map<String, Object> filterInvoices(String customerName, String paymentStatus, Integer page, Integer size) throws Exception {
		    Pageable pageable = PageRequest.of(page,size);
		    
		    Page<Invoice> invoicePage;
		    
		    if (customerName != null && paymentStatus != null) {
		        invoicePage = invoiceRepo.findByCustomerNameContainingIgnoreCaseAndPaymentStatus(customerName, PaymentStatus.valueOf(paymentStatus.toUpperCase()), pageable);
		    } else if (customerName != null) {
		        invoicePage = invoiceRepo.findByCustomerNameContainingIgnoreCase(customerName, pageable);
		    } else if (paymentStatus != null) {
		        PaymentStatus status = PaymentStatus.valueOf(paymentStatus.toUpperCase());  // String → Enum
		        invoicePage = invoiceRepo.findByPaymentStatus(status, pageable);
		    } else {
		        invoicePage = invoiceRepo.findAll(pageable);
		    }

		    List<InvoiceResponseModel> invoiceResponseModels = invoiceEntityToModelConverter.findAll(invoicePage.getContent());

		    Map<String, Object> response = new HashMap<>();
		    response.put("data", invoiceResponseModels);
		    response.put("totalRecords", invoicePage.getTotalElements());
		    response.put("pageNumber", page);
		    response.put("pageSize", size);

		    return response;
		}
		
		public List<ClientResponseModel> findByClientId(Integer clientId) throws Exception{
	        List<Invoice> invoices = invoiceRepo.findByClientId(clientId);
	        if (invoices.isEmpty()) {
	            throw new Exception("No invoices found for the given client ID.");
	        }
	        return invoiceEntityToModelConverter.mapInvoiceToClientResponse(invoices);
		}
		
		public List<InvoiceResponseModel>getInvoicesByClient(Integer clientId){
	        List<Invoice> invoices = invoiceRepo.findByClientId(clientId);
	        
	        return invoiceEntityToModelConverter.findAll(invoices);

		}

		public void softDeleteById(Integer id) throws Exception {
	        Optional<Invoice> optionalInvoice = invoiceRepo.findById(id);
	        if (optionalInvoice.isEmpty()) {
	            throw new Exception("The user account does not exist."); 
	        }
	        Invoice invoice=optionalInvoice.get();
	        if (invoice.getDeleted()) {
	            throw new Exception("This user account has already been deleted.");
			}
	        invoice.setDeleted(true);
//	        invoice.setUpdatedAt(new Timestamp(System.currentTimeMillis())); 
	        invoiceRepo.save(invoice);
		}
		
	    public void HardDelete(Integer id) throws Exception {
			  Optional<Invoice> invoiceoptional=invoiceRepo.findById(id);
			  if (invoiceoptional.isEmpty()) {
		           throw new Exception("The user  does not exist.");
			}
			  invoiceRepo.deleteById(id);
		  }
	    
	    public void RestoreById(Integer id,Integer status) throws Exception {
	        Optional<Invoice> optionalInvoice = invoiceRepo.findById(id);
	        if (optionalInvoice.isEmpty()) {
	            throw new Exception("The invoice does not exist."); 
	        }
	        Invoice invoice=optionalInvoice.get();
	        if (!invoice.getDeleted()) {
	            throw new Exception("This invoice is already active.");
			}
	        invoice.setDeleted(false);
//	        invoice.setUpdatedAt(new Timestamp(System.currentTimeMillis())); 
	        invoiceRepo.save(invoice);
		}
   }
       
