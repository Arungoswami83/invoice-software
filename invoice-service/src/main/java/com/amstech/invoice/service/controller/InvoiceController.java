package com.amstech.invoice.service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.amstech.invoice.service.entity.Invoice;
import com.amstech.invoice.service.request.model.InvoiceRequest;
import com.amstech.invoice.service.request.model.UpdateRequest;
import com.amstech.invoice.service.response.ResponseMessage;
import com.amstech.invoice.service.response.model.ClientResponseModel;
import com.amstech.invoice.service.response.model.InvoiceResponseModel;
import com.amstech.invoice.service.service.InvoiceService;


@RestController
@RequestMapping("invoices")
public class InvoiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
        LOGGER.info("InvoiceController: Object Created");
    } 
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = "application/json", produces = "application/json")    
    public ResponseMessage createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        LOGGER.info("Invoice created with Client ID: {}", invoiceRequest.getClientId());
        try {
            InvoiceResponseModel invoiceResponse = invoiceService.createInvoice(invoiceRequest);

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("invoice", invoiceResponse);
            responseMap.put("pdfUrl", invoiceResponse.getPdfUrl());

            return ResponseMessage.build().withSuccess("Invoice Created Successfully", responseMap);
        } catch (Exception e) {
            LOGGER.error("Failed to Create user invoice due to: {}", e.getMessage(), e);
            return ResponseMessage.build().withError(e.getMessage());
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(method = RequestMethod.PUT, value = "/updateInvoice", consumes = "application/json", produces = "application/json")
    public ResponseMessage updateInvoice(@RequestBody UpdateRequest updateRequest) {
        LOGGER.info("Invoice Update with :{}", updateRequest.getId());
        try {
            InvoiceResponseModel updatedInvoice = invoiceService.updateInvoice(updateRequest);
            return ResponseMessage.build().withSuccess("Invoice Updated Successfully", updatedInvoice);
        } catch (Exception e) {
            LOGGER.error("Failed to update invoice due to: {}", e.getMessage(), e);
            return ResponseMessage.build().withError(e.getMessage());
        }
    }

    @RequestMapping(method=RequestMethod.GET,value="/findById/{id}",produces="application/json")
    public ResponseMessage InvoiceById(@PathVariable("id") Integer id) {
        LOGGER.info("Fetching user detail by id: {}", id);
        try {
        	InvoiceResponseModel FindInvoice = invoiceService.findById(id);
        	return ResponseMessage.build().withSuccess("Invoice Found Successfully",FindInvoice);
        }catch (Exception e) {
			LOGGER.error("Failed to find Invoice due to :{}",e.getMessage(),e);
			return ResponseMessage.build().withError(e.getMessage());
		} 
    }
   
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(method = RequestMethod.GET,value = "/findByInvoiceNumber/{invoiceNumber}",produces = "application/json")
    	public ResponseMessage findByInvoiceNumber(@PathVariable("invoiceNumber") String invoiceNumber) {
    	    LOGGER.info("Fetching invoice detail by invoiceNumber: {}", invoiceNumber);
    	    try {
    	        InvoiceResponseModel findInvoice = invoiceService.findByInvoiceNumber(invoiceNumber);
    	        return ResponseMessage.build().withSuccess("Invoice Found Successfully", findInvoice);
    	    } catch (Exception e) {
    	        LOGGER.error("Failed to find invoice due to: {}", e.getMessage(), e);
    	        return ResponseMessage.build().withError("Invoice Not Found: " + e.getMessage());
    	    }
    	}
    
    @RequestMapping(method = RequestMethod.GET, value = "/findByClientId/{clientId}", produces = "application/json")
    public ResponseMessage findByClientId(@PathVariable("clientId") Integer clientId) {
        LOGGER.info("Fetching invoice details by clientId: {}", clientId);
        try {
            List<ClientResponseModel> clientResponseModels = invoiceService.findByClientId(clientId); 
            
            if (clientResponseModels.isEmpty()) {
                return ResponseMessage.build().withError("No invoices found for the given client ID.");
            }
            return ResponseMessage.build().withSuccess("Invoices found successfully", clientResponseModels); 
        } catch (Exception e) {
            LOGGER.error("Failed to find Client Invoice due to: {}", e.getMessage(), e);
            return ResponseMessage.build().withError(e.getMessage());
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/findAll", produces = "application/json")
    public ResponseMessage FindAll(@RequestParam("page")Integer page,@RequestParam("size")Integer size) {
        LOGGER.info("Fetching all invoices.");
        try {
            List<InvoiceResponseModel>invoiceResponse = invoiceService.AllInvoices(page, size); 
            long totalRecord = invoiceService.countAllInvoice();
            return ResponseMessage.build().withSuccess("User list found successfully").withTotalRecords(totalRecord).withPageNumber(page).withPageSize(size).withData(invoiceResponse);
        } catch (Exception e) {
        	LOGGER.error("Failed to find invoice list due to: {}", e.getMessage(), e);
            return ResponseMessage.build().withError(e.getMessage());
        }
    }  
    @RequestMapping(method = RequestMethod.GET, value = "/{clientId}/invoices", produces = "application/json")
    public ResponseMessage getInvoicesByClient(@RequestParam Integer clientId) {
    	try {
            List<InvoiceResponseModel> invoices = invoiceService.getInvoicesByClient(clientId);
            return ResponseMessage.build().withSuccess("client found with invoice");
    	}catch (Exception e) {
        	LOGGER.error("Failed to find invoice list due to: {}", e.getMessage(), e);
        	return ResponseMessage.build().withError(e.getMessage());
		}
    }
    
    @RequestMapping(method=RequestMethod.DELETE,value=("/softDelete/{id}"),produces="application/json")
    public ResponseMessage softDeleteById(@RequestParam("id") int id) throws Exception {
        LOGGER.info("Deleting Invoice by invoiceId: {}", id);
    	try {
    	 invoiceService.softDeleteById(id);
    	return ResponseMessage.build().withSuccess("Invoice soft deleted successfully.");
    }catch (RuntimeException e) {
        LOGGER.error("Failed to delete invoice due to: {}", e.getMessage(), e);
        return ResponseMessage.build().withError(e.getMessage());
    }
}
    
    @RequestMapping(method = RequestMethod.DELETE, value = ("/harddeleted/{id}"), produces = "application/json")
	public ResponseMessage HardDelete(@RequestParam("id") int id) {
		LOGGER.info("Deleting invoice by invoiceId: {}",id);
		try {
			invoiceService.HardDelete(id);
            return ResponseMessage.build().withSuccess("invoice deleted successfully");
		}catch (Exception e) {
            LOGGER.error("Failed to delete invoice list due to: {}", e.getMessage(), e);
            return ResponseMessage.build().withError(e.getMessage());
		}
}
    @RequestMapping(method=RequestMethod.DELETE,value=("/restore/{id}"),produces="application/json")
    public ResponseMessage RestoreById(@RequestParam("id") int id,@RequestParam("status")Integer status) {
     LOGGER.info("Changing Status for invoice by InvoiceId:{},status:{}",id,status);	
	try{
    	invoiceService.RestoreById(id,status);
    	if (status==0) {
            return ResponseMessage.build().withSuccess("Invoice re-activated successfully.");	
    	}else {
            return ResponseMessage.build().withSuccess("Invoice de-activated successfully.");
       } 	
	}catch (Exception e) {
	      LOGGER.error("Error to restoring  invoice ID: {}", id);
	      return ResponseMessage.build().withError(e.getMessage());
    }   
}
}

