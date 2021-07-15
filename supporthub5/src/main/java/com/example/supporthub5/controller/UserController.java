package com.example.supporthub5.controller;


import com.example.supporthub5.model.User;
import com.example.supporthub5.service.UserService;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    @ResponseBody
    public String  home() {
        return "WELCOME TO SUPPORT HUB";
    }




    @RequestMapping(value="/users" , method=RequestMethod.GET)
    public List<User> getAllSubjects()
    {
        return userService.getAllUsers();
    }



    @RequestMapping(method=RequestMethod.POST , value="/users")
    public void UserRegistration(@RequestBody User user)
    {
        userService.UserRegistration(user);
    }



    @RequestMapping(method= RequestMethod.PUT , value="/users/{id}")
    public void updateUser(@PathVariable Long id,@RequestBody User user)
    {
        userService.updateUser(id,user);
    }




}
