package com.nagarro.training.NoteSync.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.training.NoteSync.models.User;

import com.nagarro.training.NoteSync.services.impl.UserServiceImpl;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    // Initialize roles and a default admin user upon application startup
    @PostConstruct
    public void initRoleAndUser() {
        userServiceImpl.initRoleAndUser();
    }

    // Endpoint for registering a new user
    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userServiceImpl.registerNewUser(user);
    }

    // Endpoint accessible only to users with the 'Admin' role
    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "accessible to the admin only";
    }

    // Endpoint accessible only to users with the 'User' role
    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "accessible to the user only";
    }
}
