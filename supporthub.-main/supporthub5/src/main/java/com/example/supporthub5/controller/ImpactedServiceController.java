package com.example.supporthub5.controller;

import com.example.supporthub5.model.ServiceDetails;
import com.example.supporthub5.service.ImpactedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")
public class ImpactedServiceController {

    @Autowired
    private ImpactedService impactedService;

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public ResponseEntity<List<ServiceDetails>> getAllImpactedServices() {
         return impactedService.getAllImpactedServices();
    }

}


