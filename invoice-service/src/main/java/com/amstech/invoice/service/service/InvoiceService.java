package com.amstech.invoice.service.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.amstech.invoice.service.converter.entity.InvoiceModelToEntityConverter;
import com.amstech.invoice.service.converter.model.InvoiceEntityToModelConverter;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.InvoiceItem;
import com.amstech.invoice.service.entity.InvoiceType;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.entity.PaymentMethod;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.repo.CompanyRepo;
import com.amstech.invoice.service.repo.InvoiceItemRepo;
import com.amstech.invoice.service.repo.InvoiceRepo;
import com.amstech.invoice.service.repo.InvoiceTypeRepo;
import com.amstech.invoice.service.repo.PaymentRepo;
import com.amstech.invoice.service.request.model.InvoiceRequest;
import com.amstech.invoice.service.request.model.UpdateRequest;
import com.amstech.invoice.service.response.model.ClientResponseModel;
import com.amstech.invoice.service.response.model.InvoiceResponseModel;


@Service
public class InvoiceService {
	private final Logger LOGGER=LoggerFactory.getLogger(InvoiceService.class);
	
	@Autowired
	private InvoiceRepo invoiceRepo;
	
	@Autowired
	private ClientRepo clientRepo;
	
	 @Autowired
	 private InvoiceTypeRepo invoiceTypeRepo;
	 
	 @Autowired
	 private CompanyRepo companyRepo;
	 
	 @Autowired
	 private InvoiceItemRepo invoiceItemRepo;
	 
	 @Autowired
	 private PaymentRepo paymentRepo;
	 
	 @Autowired
	 private InvoiceEntityToModelConverter invoiceEntityToModelConverter;
	 
	 @Autowired
	 private InvoiceModelToEntityConverter invoiceModelToEntityConverter;
	 
	 @Autowired
	 private PDFGenerationService pdfGenerationService;
	 
	 @Autowired
	 private EmailService emailService;
	
	 public InvoiceResponseModel createInvoice(InvoiceRequest invoiceRequest) throws Exception {	 
		    Optional<Client> clientOptional = clientRepo.findById(invoiceRequest.getClientId());
		    if (!clientOptional.isPresent()) {
		        LOGGER.error("Client doesn't Exist For These Id :{}", invoiceRequest.getClientId());
		        throw new Exception("Client does not exist");
		    }
		    Optional<InvoiceType> invoiceTypesOptional = invoiceTypeRepo.findById(invoiceRequest.getInvoiceTypeId());
		    if (!invoiceTypesOptional.isPresent()) {
		        LOGGER.error("InvoiceType doesn't Exist For These Id :{}", invoiceRequest.getInvoiceTypeId());
		        throw new Exception("InvoiceType does not exist");
		    }
		    Optional<InvoiceItem> invoiceitemOptional = invoiceItemRepo.findById(invoiceRequest.getInvoiceItemsId());
		    if (!invoiceitemOptional.isPresent()) {
		        LOGGER.error("InvoiceItem doesn't Exist For These Id :{}", invoiceRequest.getInvoiceItemsId());
		        throw new Exception("InvoiceItem does not exist");
		    }
		    Optional<Company> companyOptional = companyRepo.findById(invoiceRequest.getCompanyId());
		    if (!companyOptional.isPresent()) {
		        LOGGER.error("Company doesn't Exist For These Id :{}", invoiceRequest.getCompanyId());
		        throw new Exception("Company does not exist");
		    }
		    Optional<Payment> paymentOptional = paymentRepo.findById(invoiceRequest.getPaymentId());
		    if (!paymentOptional.isPresent()) {
		        LOGGER.error("Payment doesn't Exist For These Id :{}", invoiceRequest.getPaymentId());
		        throw new Exception("Payment does not exist");
		    }
		    Payment payment = paymentOptional.get();
		    if (invoiceRequest.getPaymentMethod() != null && !invoiceRequest.getPaymentMethod().isEmpty()) {
		        payment.setPaymentMethod(PaymentMethod.fromString(invoiceRequest.getPaymentMethod()));
		        LOGGER.info("Received Payment Method: {}", invoiceRequest.getPaymentMethod());
		    } else {
		        throw new Exception("Payment Method is required");
		    }
		    paymentRepo.save(payment);

		    Invoice invoice = invoiceModelToEntityConverter.getsaveconvertToInvoiceEntity(invoiceRequest, clientOptional, companyOptional, paymentOptional, invoiceitemOptional, invoiceTypesOptional);
		    invoice.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

		    Invoice savedInvoice = invoiceRepo.save(invoice);
		    LOGGER.info("Saving Invoice with ID: {}", savedInvoice.getId()); 

		    String pdfPath = pdfGenerationService.generateInvoicePDF(savedInvoice);
		    savedInvoice.setPdfPath(pdfPath);
		    invoiceRepo.save(savedInvoice); 

		    InvoiceResponseModel responseModel = invoiceEntityToModelConverter.convertEntityToModel(savedInvoice); 
		    responseModel.setPdfUrl(pdfPath); 
		    
//		    String toEmail=clientOptional.get().getEmail();
//		    String subject ="Your Invoice-"+savedInvoice.getInvoiceNumber();
//		    String body = "Dear Customer,\n\nPlease find attached your invoice. \n\nThank you!";
//
//		    emailService.sendInvoiceEmail(toEmail, subject, body, pdfPath);  
		    return responseModel;
		}
	
	 	public InvoiceResponseModel updateInvoice(UpdateRequest updateRequest) throws Exception { 
	    LOGGER.info("Updating invoice with ID");
	    Optional<Invoice> OptionalInvoice = invoiceRepo.findById(updateRequest.getId());
	    if (!OptionalInvoice.isPresent()) {
	    	LOGGER.error("invoice doesn't Exist For These Id :{}",updateRequest.getId());
        	throw new Exception("invoice does not exist ");
        }
	    Invoice invoice= OptionalInvoice.get();
	    Invoice updateInvoice=invoiceModelToEntityConverter.updateInvoiceModel(invoice, updateRequest);
	    updateInvoice= invoiceRepo.save(updateInvoice);
	    return invoiceEntityToModelConverter.getfindById(updateInvoice);
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
			throw new Exception("The invoice number does not exist.");
		}
		Invoice invoice=invoiceoptional.get();
		if (invoice.getDeleted()) {
            throw new Exception("Your account has been deactivated. Please contact the administrator for assistance.");
		}
	    return invoiceEntityToModelConverter.getfingByInvoiceNumber(invoice);
	}
	
		public List<InvoiceResponseModel>AllInvoices(Integer page, Integer size)throws Exception  {
			List<Invoice>invoices=invoiceRepo.findAllInvoice(PageRequest.of(page, size));
			return invoiceEntityToModelConverter.findAll(invoices);
      }
	
		public long countAllInvoice() throws Exception {
	        return invoiceRepo.countAllInvoice();
	    }
		
		public List<ClientResponseModel> findByClientId(Integer clientId) throws Exception{
	        List<Invoice> invoices = invoiceRepo.findByClientId(clientId);
	        if (invoices.isEmpty()) {
	            throw new Exception("No invoices found for the given client ID.");
	        }
	        return invoiceEntityToModelConverter.mapInvoiceToClientResponse(invoices);
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
	        invoice.setUpdatedAt(new Timestamp(System.currentTimeMillis())); 
	        invoiceRepo.save(invoice);
		}
		
	    public void HardDelete(Integer id) throws Exception {
			  Optional<Invoice> invoiceoptional=invoiceRepo.findById(id);
			  if (invoiceoptional.isEmpty()) {
		           throw new Exception("The user account does not exist.");
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
	        invoice.setUpdatedAt(new Timestamp(System.currentTimeMillis())); 
	        invoiceRepo.save(invoice);
		}
   }
       
