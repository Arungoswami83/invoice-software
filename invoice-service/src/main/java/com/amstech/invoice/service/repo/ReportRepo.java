package com.amstech.invoice.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.invoice.service.entity.Report;

public interface ReportRepo extends JpaRepository<Report,Integer> {

}
