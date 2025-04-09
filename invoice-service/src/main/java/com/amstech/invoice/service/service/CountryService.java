package com.amstech.invoice.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.invoice.service.repo.CountryRepo;

@Service
public class CountryService {
    @Autowired
    private CountryRepo countryRepo;

    public List<String> getStatesByCountryId(Integer countryId) {
        return countryRepo.findStatesByCountryId(countryId);
}
}