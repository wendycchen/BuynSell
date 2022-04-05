package com.cgi.accountservice.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class UserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String token;
}
