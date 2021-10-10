package com.example.supporthub5.service;


import com.example.supporthub5.model.LoginUser;
import com.example.supporthub5.model.User;
import com.example.supporthub5.repository.UserRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;


    public String UserRegistration(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User Registered Successfully";
    }

      public ResponseEntity<List<User>> getAllUsers() {
        List<User> users=new ArrayList<User>();
        users =userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    public User updateUser(Long id,User updatedUserData) {
        User user=userRepository.findById(id).orElseThrow();
        user.setFirstName(updatedUserData.getFirstName());
        user.setLastName(updatedUserData.getLastName());
        user.setUserName(updatedUserData.getUserName());
        user.setPassword(updatedUserData.getPassword());
        user.setEmailId(updatedUserData.getEmailId());
        user.setPhoneNo(updatedUserData.getPhoneNo());
        userRepository.save(user);
        return user;
      }




    public String deleteUsers() {
        userRepository.deleteAll();
        return "users deleted successfully";
    }



    public String resetPassword(String emailId,String password,String confirmPassword) {
     
        if(password.equals(confirmPassword) &&
                userRepository.findByEmailId(emailId).isPresent())
        {
            User u=userRepository.findByEmailId(emailId).get();
            System.out.println(u);
            u.setPassword(confirmPassword);
            userRepository.save(u);
            return "Password set succesfully";
        }
        return "Something went wrong!!Please try again";
    }



    public String forgotPassword(String emailId) {
       
         if(userRepository.findByEmailId(emailId)!=null){
              return emailService.sendEmail(emailId);
             }
          else
            return "Please provide a valid emailId";
    }




}
