package com.example.supporthub5.service;

import com.example.supporthub5.model.Location;
import com.example.supporthub5.model.User;
import com.example.supporthub5.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {



    @Autowired
    private LocationRepository locationRepository;


    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations=new ArrayList<Location>();
        locations =locationRepository.findAll();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }



}
