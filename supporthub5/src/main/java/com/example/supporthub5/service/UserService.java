package com.example.supporthub5.service;

import com.example.exception.ResourceNotFoundException;
import com.example.supporthub5.model.User;
import com.example.supporthub5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void UserRegistration(User user) {
        userRepository.save(user);
    }


    public User updateUser(Long id,User user) {

        User updateUser=userRepository.findById(id).orElseThrow(
                ()  -> new ResourceNotFoundException("id",id));

        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getFirstName());
        updateUser.setPassword(user.getPassword());
        updateUser.setEmailId(user.getEmailId());
        updateUser.setPhoneNo(user.getPhoneNo());

        userRepository.save(updateUser);
        return updateUser;



    }






}