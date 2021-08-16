package com.example.supporthub5.service;


import com.example.supporthub5.model.Request;
import com.example.supporthub5.model.User;
import com.example.supporthub5.repository.ImpactedServiceRepository;
import com.example.supporthub5.repository.LocationRepository;
import com.example.supporthub5.repository.RequestRepository;
import com.example.supporthub5.repository.UserRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ImpactedServiceRepository impactedServiceRepository;


    public String newRequest(Request request) {
        if (validateRequests(request)) {
            requestRepository.save(request);
            return "Request Submitted Successfully";
        }
        return "Error in  Submitting your  Request";
    }


    public Boolean validateRequests(Request request) {
        long userId = request.getUser().getId();
        long locationId = request.getLocation().getLocationId();
        long serviceId = request.getService().getServiceId();

        if (userRepository.findById(userId).isPresent() &&
                locationRepository.findByLocationId(locationId).isPresent() &&
                impactedServiceRepository.findByServiceId(serviceId).isPresent())
            return true;

        else
            return false;
    }


    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestRepository.findAll();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }


    public String deleteAllRequests() {
        requestRepository.deleteAll();
        return "requests deleted successfully";
    }


    public ResponseEntity<List<Request>> getMyRequests(ObjectNode objectNode) {
        String username=objectNode.get("userName").asText();
        List<Request> requests = new ArrayList<Request>();
        if (userRepository.findByUserName(username) != null) {
            User user = userRepository.findByUserName(username);
            System.out.println(user);
            long userId = user.getId();
           requests = requestRepository.findByUser(user);
        }
            return new ResponseEntity<>(requests, HttpStatus.OK);

    }

}
