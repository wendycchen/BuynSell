package com.cgi.accountservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class LoginRequest {
    private String email;
    private String password;
}
