package com.amstech.invoice.service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amstech.invoice.service.entity.Client;
@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {

	
}