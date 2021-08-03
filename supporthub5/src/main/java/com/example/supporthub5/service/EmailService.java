package com.example.supporthub5.service;

import com.example.supporthub5.model.EmailRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.Random;

@Service
public class EmailService {



    @Autowired
    private JavaMailSender javaMailSender;

       public ResponseEntity<?>  sendEmail(EmailRequest request){

        String from = "vaspularuchi@gmail.com";
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(request.getFrom());
        message.setSubject(request.getSubject());

        Random random = new Random(1000);
        int otp = random.nextInt(100000);

        message.setText("YOUR  ONE-TIME PASSWORD: " + otp);
        javaMailSender.send(message);


        return ResponseEntity.ok("Email sent successfully");
    }


}
