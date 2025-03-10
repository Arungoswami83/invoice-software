package com.amstech.invoice.service.converter.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.invoice.service.entity.ProductInvoice;
import com.amstech.invoice.service.response.model.ProductInvoiceResponseModel;

@Component
public class ProductInvoiceEntityToModelConverter {
	
	public ProductInvoiceResponseModel getfindByIdConverter(ProductInvoice productInvoice) {
		ProductInvoiceResponseModel responseModel = new ProductInvoiceResponseModel();
		responseModel.setId(productInvoice.getId());
		responseModel.setAccountDetails(productInvoice.getAccountDetails());
		responseModel.setBuyerDetails(productInvoice.getBuyerDetails());
		responseModel.setSupplier(productInvoice.getSupplier());
	
        
		return responseModel;
	}

	public List<ProductInvoiceResponseModel> getfindAllConverter(List<ProductInvoice> productInvoiceList) {

		List<ProductInvoiceResponseModel> productInvoiceResponseModels = new ArrayList<>();

		for (ProductInvoice productInvoice : productInvoiceList) {
			ProductInvoiceResponseModel responseModel = new ProductInvoiceResponseModel();
			responseModel.setId(productInvoice.getId());
			responseModel.setAccountDetails(productInvoice.getAccountDetails());
			responseModel.setBuyerDetails(productInvoice.getBuyerDetails());
			responseModel.setSupplier(productInvoice.getSupplier());

			productInvoiceResponseModels.add(responseModel);
		}

		return productInvoiceResponseModels;
	}
}
