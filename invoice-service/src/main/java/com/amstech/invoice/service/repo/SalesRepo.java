package com.amstech.invoice.service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amstech.invoice.service.entity.SalesInvoices;



@Repository
public interface SalesRepo extends JpaRepository<SalesInvoices, Integer> {
	
    SalesInvoices findByInvoiceNumber(String invoiceNumber);
    
    @Query("SELECT s FROM SalesInvoices s") 
    List<SalesInvoices> findAll();

    Optional<SalesInvoices> findById(Integer id);

    List<SalesInvoices> findByIsDeleted(int isDeleted);
    
    @Transactional
    @Modifying
    @Query("UPDATE SalesInvoices s SET s.isDeleted = 1 WHERE s.id = :id")
    void softDeleteSalesInvoices(@Param("id") int id);
    @Modifying
    @Query("UPDATE SalesInvoices s SET s.isDeleted = 0 WHERE s.id = :id")
    void restoreSalesInvoices(@Param("id") Integer id);

}