package com.example.supporthub5.service;

import com.example.supporthub5.model.Request;
import com.example.supporthub5.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {


    @Autowired
    private RequestRepository requestRepository;


    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests=new ArrayList<Request>();
        requests =requestRepository.findAll();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }


    public String openRequest(Request request) {

        requestRepository.save(request);
        return "Request Submitted Successfully";
    }

//    public ResponseEntity<List<Request>> getMyRequests(String username) {
//        List<Request> requests=new ArrayList<Request>();
//        requests =requestRepository.findByUserName(username);
//        return new ResponseEntity<>(requests, HttpStatus.OK);
//
//    }
}
