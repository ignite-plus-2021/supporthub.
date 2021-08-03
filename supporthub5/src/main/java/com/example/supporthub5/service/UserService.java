package com.example.supporthub5.service;

import com.example.supporthub5.model.User;
import com.example.supporthub5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public List<User> getAllUsers() {
        return userRepository.findAll();
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

        userRepository.save(updateUser);
        return updateUser;



    }









}