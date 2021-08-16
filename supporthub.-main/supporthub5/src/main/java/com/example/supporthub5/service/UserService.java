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


    public User updateUser(Long id,User user) {
          User updateUser=userRepository.findById(id).orElseThrow();
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setUserName(user.getUserName());
        updateUser.setPassword(user.getPassword());
        updateUser.setEmailId(user.getEmailId());
        updateUser.setPhoneNo(user.getPhoneNo());
        updateUser.setLoggedIn(user.getLoggedIn());
        userRepository.save(updateUser);
        return updateUser;
      }



    public User login(LoginUser user) {
          String username=user.getUserName();
          String password=user.getPassword();
          User u=userRepository.findByUserNameAndPassword(username,password);
          u.setLoggedIn(Boolean.TRUE); ///needs to change this
          userRepository.save(u);
          return u;
    }


    public String deleteUsers() {
        userRepository.deleteAll();
        return "users deleted successfully";
    }

    //Need to change this
    public User logOut(LoginUser user) {
        String username=user.getUserName();
        String password=user.getPassword();
        User u=userRepository.findByUserNameAndPassword(username,password);
        u.setLoggedIn(Boolean.FALSE);
        userRepository.save(u);
        return u;
    }


    public String resetPassword(ObjectNode objectNode) {
        String emailId=objectNode.get("emailId").asText();
        String password=objectNode.get("password").asText();
        String confirmPassword=objectNode.get("confirmPassword").asText();

        if(password.equals(confirmPassword) &&
                userRepository.findByEmailId(emailId)!=null)
        {
            User u=userRepository.findByEmailId(emailId);
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