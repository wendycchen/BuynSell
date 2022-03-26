package com.cgi.accountservice.controller;

import com.cgi.accountservice.exceptions.*;
import com.cgi.accountservice.models.LoginRequest;
import com.cgi.accountservice.models.RegistrationRequest;
import com.cgi.accountservice.services.RegistrationService;
import com.cgi.accountservice.util.JwtUtil;
import com.cgi.accountservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final RegistrationService registrationService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AccountController(RegistrationService registrationService, UserService userService, JwtUtil jwtUtil){
        this.registrationService = registrationService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest regReq) throws UsernameAlreadyExistsException, EmailAndUsernameExists, EmailAlreadyExistsException {
        try{
            String email =  registrationService.register(regReq);
            return ResponseEntity.ok().body(email);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/confirm/{token}")
    public String confirmEmailConfirmation(@PathVariable ("token") String token) {
        try {
            return registrationService.confirmToken(token);
        } catch (TokenNotFoundException | EmailAlreadyConfirmedException | TokenExpiredException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateJwtToken(@RequestBody LoginRequest loginRequest) {
        String token = jwtUtil.generateToken((User) userService.loadUserByUsername(loginRequest.getEmail()));
        return  ResponseEntity.ok().body(token);
    }



}
