package com.nagarro.training.NoteSync.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.training.NoteSync.models.Role;
import com.nagarro.training.NoteSync.repositories.RoleRepository;

@Service
public class RoleServiceImpl {

    @Autowired
    private RoleRepository roleRepository;

    // Create a new role by saving it in the database
    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }
}
