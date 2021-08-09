package com.example.supporthub5.service;

import ch.qos.logback.core.status.Status;
import com.example.supporthub5.model.LoginUser;
import com.example.supporthub5.model.User;
import com.example.supporthub5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users=new ArrayList<User>();
        users =userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    public String UserRegistration(User user) {

//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User Registered Successfully";
    }


    public User updateUser(Long id,User user) {

        User updateUser=userRepository.findById(id).orElseThrow();
              // ()  -> new ResourceNotFoundException("id",id));
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
       u.setLoggedIn(Boolean.TRUE);
       userRepository.save(u);
       return u;
    }



    public String deleteUsers() {
        userRepository.deleteAll();
        return "users deleted successfully";
    }


    public User logOut(LoginUser user) {
        String username=user.getUserName();
        String password=user.getPassword();
        User u=userRepository.findByUserNameAndPassword(username,password);
        u.setLoggedIn(Boolean.FALSE);
        userRepository.save(u);
        return u;

    }

    public User resetPassword(LoginUser user) {
        String username=user.getUserName();
        String password=user.getPassword();
        User u=userRepository.findByUserNameAndPassword(username,password);
        u.setPassword(password);//how to take this info from user(new password)
        userRepository.save(u);
        return u;

    }
}