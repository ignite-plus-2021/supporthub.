package com.example.supporthub5.service;

import com.example.supporthub5.model.Location;
import com.example.supporthub5.model.ServiceDetails;
import com.example.supporthub5.repository.ImpactedServiceRepository;
import com.example.supporthub5.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImpactedService {



    @Autowired
    private ImpactedServiceRepository impactedServiceRepository;

    public ResponseEntity<List<ServiceDetails>> getAllServices() {
        List<ServiceDetails> services=new ArrayList<ServiceDetails>();
        services =impactedServiceRepository.findAll();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }




}
