package com.example.supporthub5.controller;

import com.example.supporthub5.model.Location;
import com.example.supporthub5.model.Request;
import com.example.supporthub5.repository.RequestRepository;
import com.example.supporthub5.service.RequestService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/requests")
public class RequestController {


    @Autowired
    RequestService requestService;

    @RequestMapping(value = "/newrequest",method = RequestMethod.POST)
    public String openRequest(@RequestBody Request request) {
        return requestService.newRequest(request);
    }


    @RequestMapping(value = "/allrequests", method = RequestMethod.GET)
    public ResponseEntity<List<Request>> getAllRequests() {
        return requestService.getAllRequests();
    }


    @RequestMapping(value = "/myrequests", method = RequestMethod.POST)
    public ResponseEntity<List<Request>> getMyRequests(@RequestBody ObjectNode objectNode) {
        return requestService.getMyRequests(objectNode);
    }


    @RequestMapping( value = "/delete",method = RequestMethod.DELETE)
    public String deleteRequests() {
        return requestService.deleteAllRequests();
    }



    @RequestMapping(value="/closerequests",method=RequestMethod.POST)
    public ResponseEntity<String> closeRequests(Long[] requestId)
    {
        return requestService.closeRequests(requestId);
    }


    @RequestMapping("/filters",method=RequestMethod.GET)
    public ResponseEntity<List<Request>> filters(@RequestParam Long requestId, @RequestParam  Long locationId,@RequestParam Long serviceId,@RequestParam  String description,@RequestParam  String state,@RequestParam  String createdOn)throws Exception

    {
          return  requestService.filters(requestId,locationId,serviceId,description,state,createdOn);
    }



}
