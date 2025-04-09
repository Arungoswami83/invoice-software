package com.amstech.invoice.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.service.StateService;

@RestController
@RequestMapping("/api/states")
public class StateController {
    @Autowired
    private StateService stateService;

    @GetMapping("/{stateId}/country")
    public ResponseEntity<String> getCountryByStateId(@PathVariable Integer stateId) {
        String country = stateService.getCountryByStateId(stateId);
        return ResponseEntity.ok(country);
    }
}
