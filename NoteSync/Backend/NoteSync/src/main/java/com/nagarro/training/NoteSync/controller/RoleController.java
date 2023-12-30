package com.nagarro.training.NoteSync.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.training.NoteSync.models.Role;
import com.nagarro.training.NoteSync.services.impl.RoleServiceImpl;

@RestController
public class RoleController {

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    // Endpoint for creating a new role
    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleServiceImpl.createNewRole(role);
    }
}
