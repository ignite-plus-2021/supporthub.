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


    public String UserRegistration(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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



    public User login(LoginUser user) {
          String username=user.getUserName();
          String password=user.getPassword();
          User u=userRepository.findByUserNameAndPassword(username,password).get();
//          u.setLoggedIn(Boolean.TRUE); ///needs to change this
          userRepository.save(u);
          return u;
    }


    public String deleteUsers() {
        userRepository.deleteAll();
        return "users deleted successfully";
    }





    public String resetPassword(ObjectNode objectNode) {
        String emailId=objectNode.get("emailId").asText();
        String password=objectNode.get("password").asText();
        String confirmPassword=objectNode.get("confirmPassword").asText();

        if(password.equals(confirmPassword) &&
                userRepository.findByEmailId(emailId).isPresent())
        {
            User u=userRepository.findByEmailId(emailId).get();
            System.out.println(u);
            u.setPassword(confirmPassword);
            userRepository.save(u);
            return "Password set succesfully";
        }
        return "Something went wrong";
    }



    public String forgotPassword(ObjectNode objectNode) {
        String emailId = objectNode.get("emailId").asText();
         System.out.println(emailId);
         if(userRepository.findByEmailId(emailId)!=null)
            return "User with email exists";
        else
            return "User not found";
    }




}