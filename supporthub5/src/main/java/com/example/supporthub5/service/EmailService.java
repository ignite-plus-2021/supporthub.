package com.example.supporthub5.service;

import com.example.supporthub5.model.EmailRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

       public ResponseEntity<?>  sendEmail(String emailId) {

        String from = "vaspularuchi@gmail.com";
        SimpleMailMessage message = new SimpleMailMessage();
          
           message.setFrom(from);
            message.setTo(emailId);
            message.setSubject("OTP from SupportHub");

            Random random = new Random(1000);
            int otp = 100000+random.nextInt(900000);

            message.setText("YOUR  ONE-TIME PASSWORD: " + otp);
            javaMailSender.send(message);

          return ResponseEntity.ok("Email sent sucessfully");

    }


}
