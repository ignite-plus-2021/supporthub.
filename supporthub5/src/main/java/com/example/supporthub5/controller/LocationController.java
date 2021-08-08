package com.example.supporthub5.controller;

import com.example.supporthub5.model.Location;
import com.example.supporthub5.service.LocationService;
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
public class LocationController {



    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public ResponseEntity<List<Location>> getAllLocations() {

          return locationService.getAllLocations();
    }

}
