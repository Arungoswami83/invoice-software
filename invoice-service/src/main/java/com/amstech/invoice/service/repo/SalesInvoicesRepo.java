package com.amstech.invoice.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.invoice.service.entity.SalesInvoices;

public interface SalesInvoicesRepo extends JpaRepository<SalesInvoices, Integer>{

}
