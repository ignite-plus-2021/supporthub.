package com.example.supporthub5.service;

import ch.qos.logback.core.status.Status;
import com.example.supporthub5.model.LoginUser;
import com.example.supporthub5.model.User;
import com.example.supporthub5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;




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
        updateUser.setLoggedIn(user.getLoggedIn());
        userRepository.save(updateUser);
        return updateUser;



    }



    public User login(LoginUser user) {

        String username=user.getUserName();
        String password=user.getPassword();
       User u=userRepository.findByUserNameAndPassword(username,password);
       u.setLoggedIn(1);
       userRepository.save(u);
       return u;
    }



    public String deleteUsers() {
        userRepository.deleteAll();
        return "users deleted successfully";
    }


    public User logout(LoginUser user) {
        String username=user.getUserName();
        String password=user.getPassword();
        User u=userRepository.findByUserNameAndPassword(username,password);
        u.setLoggedIn(0);
        userRepository.save(u);
        return u;

    }
}