package com.amstech.invoice.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.service.CountryService;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/{countryId}/states")
    public ResponseEntity<List<String>> getStatesByCountryId(@PathVariable Integer countryId) {
        List<String> states = countryService.getStatesByCountryId(countryId);
        return ResponseEntity.ok(states);
    }
}
