package com.example.supporthub5.controller;

import com.example.supporthub5.model.Request;
import com.example.supporthub5.service.RequestService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/requests")
public class RequestController {


    @Autowired
    RequestService requestService;


    @RequestMapping(method = RequestMethod.POST, value = "/newrequest")
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



    @RequestMapping(method = RequestMethod.DELETE, value = "delete")
    public String deleteRequests() {
        return requestService.deleteAllRequests();
    }

//    @RequestMapping(value="/requestid",method=RequestMethod.GET)
//    public ResponseEntity<String> generateRequestId()
//    {
//        return requestService.generateRequestId();
//    }


}
