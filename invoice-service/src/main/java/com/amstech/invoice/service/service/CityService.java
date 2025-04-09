package com.amstech.invoice.service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.invoice.service.entity.City;
import com.amstech.invoice.service.repo.CityRepo;

@Service
public class CityService {
    @Autowired
    private CityRepo cityRepo;

    
    public String getStateByCityId(Integer cityId) {
        return cityRepo.findStateByCityId(cityId);
    }

   
    public String getStateByCityName(String cityName) {
        return cityRepo.findStateByCityName(cityName);
    }

    
    public List<String> getCitiesByStateId(Integer stateId) {
        List<City> cityList = cityRepo.findCitiesByStateId(stateId);
        List<String> cityNames = new ArrayList<>();
        for (City city : cityList) {
            cityNames.add(city.getName());  
        }
        return cityNames;
    }
}
