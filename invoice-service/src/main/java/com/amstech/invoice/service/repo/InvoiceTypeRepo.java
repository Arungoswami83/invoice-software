package com.amstech.invoice.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amstech.invoice.service.entity.InvoiceType;

public interface InvoiceTypeRepo extends JpaRepository<InvoiceType, Integer> {

}
