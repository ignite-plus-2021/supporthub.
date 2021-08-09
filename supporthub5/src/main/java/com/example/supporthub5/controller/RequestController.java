package com.example.supporthub5.controller;

import com.example.supporthub5.model.Request;
import com.example.supporthub5.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestController {


    @Autowired
    RequestService requestService;

    @RequestMapping(value = "/allrequests", method = RequestMethod.GET)
    public ResponseEntity<List<Request>> getAllRequests() {
        return requestService.getAllRequests();
    }

//    @RequestMapping(value = "/myrequests", method = RequestMethod.GET)
//    public ResponseEntity<List<Request>> getMyRequests(@RequestBody String username) {
//        return requestService.getMyRequests(username);
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/newrequest")
    public String openRequest(@RequestBody Request request) {
        return requestService.openRequest(request);
    }
}
