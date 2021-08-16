package com.example.supporthub5.controller;

import com.example.supporthub5.model.EmailRequest;
import com.example.supporthub5.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EmailController {

   @Autowired
   private EmailService emailService;

    @RequestMapping(value="/sendmail",method= RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
        System.out.println(request);
        return emailService.sendEmail(request);
    }
}
