package com.amstech.invoice.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.invoice.service.entity.Company;

public interface CompanyRepo extends JpaRepository<Company,Integer> {

}
