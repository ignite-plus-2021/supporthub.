package com.example.supporthub5.controller;


import com.example.supporthub5.model.LoginUser;
import com.example.supporthub5.model.User;
import com.example.supporthub5.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String UserRegistration(@RequestBody User user) {
        return userService.UserRegistration(user);
    }


    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }



    @RequestMapping(method = RequestMethod.DELETE , value="/delete")
    public String deleteUsers() {
        return userService.deleteUsers();
      }


    @RequestMapping(method=RequestMethod.PUT,value="/resetpassword")
    public String resetPassword(@RequestParam String emailId,@RequestParam String password,@RequestParam String confirmPassword) {
        return userService.resetPassword(emailId,password,confirmPassword);
    }



    @RequestMapping(method=Request.POST,value="/forgotpassword")
    public String forgotPassword(@RequestParam String emailId ){
        return  userService.forgotPassword(emailId);
    }

    
    
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public User login(@Valid @RequestBody LoginUser user) {
        return userService.login(user);
    }


}



