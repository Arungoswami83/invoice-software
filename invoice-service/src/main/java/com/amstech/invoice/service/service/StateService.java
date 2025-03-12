package com.amstech.invoice.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.invoice.service.repo.StateRepo;

@Service
public class StateService {
    @Autowired
    private StateRepo stateRepository;

    public String getCountryByStateId(Integer stateId) {
        return stateRepository.findCountryByStateId(stateId);
    }
}
