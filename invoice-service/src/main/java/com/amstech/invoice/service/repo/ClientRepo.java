package com.amstech.invoice.service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.invoice.service.entity.Client;
import com.amstech.invoice.service.entity.Invoice;

public interface ClientRepo extends JpaRepository<Client,Integer> {
	 boolean existsById(int i);   //if user exist then user can be create a invoice
	
}