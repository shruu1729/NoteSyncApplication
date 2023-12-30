package com.nagarro.training.NoteSync.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.training.NoteSync.models.JwtRequest;
import com.nagarro.training.NoteSync.models.JwtResponse;
import com.nagarro.training.NoteSync.services.impl.AuthServiceImpl;

@CrossOrigin
@RestController
public class AuthController {
	
    @Autowired
    private AuthServiceImpl authServiceImpl;

    // Endpoint for authenticating and generating a JWT token
    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return authServiceImpl.createJwtToken(jwtRequest);
    }
}