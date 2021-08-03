package com.example.supporthub5.controller;


import com.example.supporthub5.model.LoginUser;
import com.example.supporthub5.model.User;
import com.example.supporthub5.repository.UserRepository;
import com.example.supporthub5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method=RequestMethod.POST , value="/register")
    public String UserRegistration(@RequestBody User user)
    {
        return userService.UserRegistration(user);
    }



    @RequestMapping(method= RequestMethod.PUT , value="/users/{id}")
    public void updateUser(@PathVariable Long id,@RequestBody User user)
    {
        userService.updateUser(id,user);
    }


    @PutMapping("/login")
    public User login(@Valid @RequestBody LoginUser user) {
        return  userService.login(user);
    }


    @DeleteMapping("/users/delete")
    public String  deleteUsers() {
        return userService.deleteUsers();


    }


    @PutMapping("/logout")
    public User logout(@Valid @RequestBody LoginUser user) {
        return  userService.logout(user);
    }

    }


