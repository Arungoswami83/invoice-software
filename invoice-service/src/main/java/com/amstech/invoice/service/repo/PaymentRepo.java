package com.amstech.invoice.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amstech.invoice.service.entity.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {
    List<Payment> findByInvoiceId(Integer invoiceId); 
}
