package com.amstech.invoice.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.invoice.service.entity.InvoiceItem;

public interface InvoiceItemRepo extends JpaRepository<InvoiceItem, Integer> {

}
