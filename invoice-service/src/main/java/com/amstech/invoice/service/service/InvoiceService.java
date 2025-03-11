package com.amstech.invoice.service.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.aspectj.lang.reflect.CatchClauseSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.amstech.invoice.service.converter.entity.InvoiceModelToEntityConverter;
import com.amstech.invoice.service.converter.model.InvoiceEntityToModelConverter;
import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Company;
import com.amstech.invoice.service.entity.GenerateInvoice;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.entity.InvoiceItem;
import com.amstech.invoice.service.entity.InvoiceType;
import com.amstech.invoice.service.entity.Payment;
import com.amstech.invoice.service.repo.ClientRepo;
import com.amstech.invoice.service.repo.CompanyRepo;
import com.amstech.invoice.service.repo.InvoiceItemRepo;
import com.amstech.invoice.service.repo.InvoiceRepository;
import com.amstech.invoice.service.repo.InvoiceTypeRepo;
import com.amstech.invoice.service.repo.PaymentRepo;
import com.amstech.invoice.service.request.model.InvoiceRequest;
import com.amstech.invoice.service.request.model.UpdateRequest;
import com.amstech.invoice.service.response.model.InvoiceResponseModel;
import jakarta.transaction.Transactional;

@Service
public class InvoiceService {
	private final Logger LOGGER=LoggerFactory.getLogger(InvoiceService.class);
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
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
	
	public InvoiceResponseModel createInvoice(InvoiceRequest invoiceRequest) throws Exception {	 
	   Optional <Client> clientOptional = clientRepo.findById(invoiceRequest.getClientId());
	  
        if (!clientOptional.isPresent()) {
        	LOGGER.error("Client does'nt Exist For These Id :{}",invoiceRequest.getClientId());
        	throw new Exception("client does not exist ");
        }
        
        Optional<InvoiceType> invoiceTypesOptional = invoiceTypeRepo.findById(invoiceRequest.getInvoiceTypeId());
        if (!invoiceTypesOptional.isPresent()) {
        	LOGGER.error("InvoiceType does'nt Exist For These Id :{}",invoiceRequest.getInvoiceTypeId());
        	throw new Exception("InvoiceType does not exist ");
        }
        
        Optional<InvoiceItem>invoiceitemOptional = invoiceItemRepo.findById(invoiceRequest.getInvoiceItemsId());
        if (!invoiceitemOptional.isPresent()) {
        	LOGGER.error("InvoiceItem does'nt Exist For These Id :{}",invoiceRequest.getInvoiceItemsId());
        	throw new Exception("InvoiceItem does not exist ");
        }
        
        Optional<Company> companyOptional= companyRepo.findById(invoiceRequest.getCompanyId());
	    if (!companyOptional.isPresent()) {
	    	LOGGER.error("Company does'nt Exist For These Id :{}",invoiceRequest.getCompanyId());
        	throw new Exception("Company does not exist ");
        }
	    Optional<Payment> paymentOptional = paymentRepo.findById(invoiceRequest.getPaymentId());
	    if (!paymentOptional.isPresent()) {
	    	LOGGER.error("Payment does'nt Exist For These Id :{}",invoiceRequest.getPaymentId());
        	throw new Exception("Payment does not exist ");
        }
	    Invoice invoice = invoiceModelToEntityConverter.getsaveconvertToInvoiceEntity(invoiceRequest, clientOptional, companyOptional, paymentOptional, invoiceitemOptional, invoiceTypesOptional);
	    
	    invoice.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());  	   
	    
	    Invoice saveinvoice = invoiceRepository.save(invoice);
	    
	    return invoiceEntityToModelConverter.getfindById(saveinvoice);       
	}
	
	public InvoiceResponseModel updateInvoice(UpdateRequest updateRequest) throws Exception { 
	    LOGGER.info("Updating invoice with ID");
	    Optional<Invoice> OptionalInvoice = invoiceRepository.findById(updateRequest.getId());
	    if (!OptionalInvoice.isPresent()) {
	    	LOGGER.error("invoice doesn't Exist For These Id :{}",updateRequest.getId());
        	throw new Exception("invoice does not exist ");
        }
	    Invoice invoice= OptionalInvoice.get();
	    Invoice updateInvoice=invoiceModelToEntityConverter.updateInvoiceModel(invoice, updateRequest);
	    updateInvoice= invoiceRepository.save(updateInvoice);
	    return invoiceEntityToModelConverter.getfindById(updateInvoice);
	}
	
	public InvoiceResponseModel findById(Integer id)  throws Exception {
		Optional<Invoice>invoiceoptional=invoiceRepository.findById(id);
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
		Optional<Invoice>invoiceoptional=invoiceRepository.findByInvoiceNumber(invoiceNumber);
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
			List<Invoice>invoices=invoiceRepository.findAllInvoice(PageRequest.of(page, size));
			return invoiceEntityToModelConverter.findAll(invoices);
      }
	
		public long countAllInvoice() throws Exception {
	        return invoiceRepository.countAllInvoice();
	    }
		
		public void softDeleteById(Integer id) throws Exception {
	        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
	        if (optionalInvoice.isEmpty()) {
	            throw new Exception("The user account does not exist."); 
	        }
	        Invoice invoice=optionalInvoice.get();
	        if (invoice.getDeleted()) {
	            throw new Exception("This user account has already been deleted.");
			}
	        invoice.setDeleted(true);
	        invoice.setUpdatedAt(new Timestamp(System.currentTimeMillis())); 
	        invoiceRepository.save(invoice);
		}
		
	    public void HardDelete(Integer id) throws Exception {
			  Optional<Invoice> invoiceoptional=invoiceRepository.findById(id);
			  if (invoiceoptional.isEmpty()) {
		           throw new Exception("The user account does not exist.");
			}
			  invoiceRepository.deleteById(id);
		  }
	    
	    public void RestoreById(Integer id,Integer status) throws Exception {
	        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
	        if (optionalInvoice.isEmpty()) {
	            throw new Exception("The invoice does not exist."); 
	        }
	        Invoice invoice=optionalInvoice.get();
	        if (!invoice.getDeleted()) {
	            throw new Exception("This invoice is already active.");
			}
	        invoice.setDeleted(false);
	        invoice.setUpdatedAt(new Timestamp(System.currentTimeMillis())); 
	        invoiceRepository.save(invoice);
		}
   }
       
