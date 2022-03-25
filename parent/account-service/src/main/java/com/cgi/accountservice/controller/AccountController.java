package com.cgi.accountservice.controller;

import com.cgi.accountservice.exceptions.EmailAlreadyConfirmedException;
import com.cgi.accountservice.exceptions.EmailNotValidException;
import com.cgi.accountservice.exceptions.TokenExpiredException;
import com.cgi.accountservice.exceptions.TokenNotFoundException;
import com.cgi.accountservice.models.LoginRequest;
import com.cgi.accountservice.models.RegistrationRequest;
import com.cgi.accountservice.security.JwtUtil;
import com.cgi.accountservice.services.RegistrationService;
import com.cgi.accountservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private RegistrationService registrationService;
    private UserService userService;
    private JwtUtil jwtUtil;

    @Autowired
    public AccountController(RegistrationService registrationService, UserService userService, JwtUtil jwtUtil){
        this.registrationService = registrationService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest regReq) {
        //TODO is just returning the confirmation token for now
        try {
            return registrationService.register(regReq);
        } catch (EmailNotValidException e) {
            return e.getMessage();
        }

    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam ("token") String token) {
        try {
            return registrationService.confirmToken(token);
        } catch (TokenNotFoundException | EmailAlreadyConfirmedException | TokenExpiredException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> generateJwtToken(@RequestBody LoginRequest loginRequest) {

        String token = jwtUtil.generateToken((User) userService.loadUserByUsername(loginRequest.getEmail()));
        return  ResponseEntity.ok().body(token);
        //TODO return user?
    }

}
