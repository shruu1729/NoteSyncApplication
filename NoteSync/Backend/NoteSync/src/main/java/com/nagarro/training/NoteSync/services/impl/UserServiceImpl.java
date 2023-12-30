package com.nagarro.training.NoteSync.services.impl;

import java.util.HashSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarro.training.NoteSync.models.Role;
import com.nagarro.training.NoteSync.models.User;
import com.nagarro.training.NoteSync.repositories.RoleRepository;
import com.nagarro.training.NoteSync.repositories.UserRepository;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Initialize roles and an admin user
    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleRepository.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);

    }

    // Register a new user
    public User registerNewUser(User user) {
    	Iterable<User> allUsers = userRepository.findAll();
    	for (User temp : allUsers) {
    	    // Check if the username already exists
    	    if(temp.getUserName().equals(user.getUserName()))
    	    	return null;
    	}

        Role role = roleRepository.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userRepository.save(user);
    }

    // Get the encoded password using the PasswordEncoder
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
