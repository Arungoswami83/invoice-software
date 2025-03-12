package com.amstech.invoice.service.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.invoice.service.service.CityService;
@RestController
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    private CityService cityService;
    
    @GetMapping("/{cityId}/state")
    public ResponseEntity<String> getStateByCityId(@PathVariable Integer cityId) {
        String state = cityService.getStateByCityId(cityId);
        return ResponseEntity.ok(state);
    }

    // ✅ FIXED: Correct Mapping for City Name Query Parameter
    @GetMapping("/getStateByCityName")
    public ResponseEntity<String> getStateByCityName(@RequestParam String cityName) {
        String state = cityService.getStateByCityName(cityName);
        return ResponseEntity.ok(state);
    }

    @GetMapping("/state/{stateId}")
    public ResponseEntity<List<String>> getCitiesByStateId(@PathVariable Integer stateId) {
        List<String> cities = cityService.getCitiesByStateId(stateId);
        return ResponseEntity.ok(cities);
    }
    
}


