package com.amstech.invoice.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.invoice.service.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer>{

}
