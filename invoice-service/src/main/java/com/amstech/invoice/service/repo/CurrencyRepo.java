package com.amstech.invoice.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amstech.invoice.service.entity.Currency;
@Repository
public interface CurrencyRepo extends JpaRepository<Currency, Integer>{

}
