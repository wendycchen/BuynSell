package com.cgi.accountservice.controller;

import com.cgi.accountservice.exceptions.*;
import com.cgi.accountservice.models.LoginRequest;
import com.cgi.accountservice.models.RegistrationRequest;
import com.cgi.accountservice.services.RegistrationService;
import com.cgi.accountservice.services.UserService;
import com.cgi.accountservice.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    private final RegistrationService registrationService;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AccountController(RegistrationService registrationService, UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager){
        this.registrationService = registrationService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest regReq) throws UsernameAlreadyExistsException, EmailAndUsernameExists, EmailAlreadyExistsException {
        try{
            registrationService.register(regReq);
            log.info("User with email: {}, username: {}, firstname: {}, lastname: {} has registered",regReq.getEmail(),regReq.getUsername(),regReq.getFirstName(),regReq.getLastName());
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/confirm")
    public void confirmEmailConfirmation(@RequestParam("token") String token) {
        try {
            registrationService.confirmEmailToken(token);
        } catch (TokenNotFoundException | EmailAlreadyConfirmedException | TokenExpiredException e) {
           log.info("Error confirming account using token: {}",token);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            //Using springs authentication manager to check if login credentials are correct
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
            } catch (AuthenticationException e) {
            //If the login credentials are not correct
                log.info("invalid login attempt from user email: {}", loginRequest.getEmail());
                return ResponseEntity.badRequest().build();
            }
            //Grabbing our user to
            User user = (User) userService.loadUserByUsername(loginRequest.getEmail());
            //If the login credentials are correct, we generate a JWT token using the email, username and id as subjects.
            String token = jwtUtil.generateToken(user);
            log.info("Successful login from user: {} with role(s): {}",user.getUsername(),user.getAuthorities());
            return ResponseEntity.ok().body(token);
        }
}
