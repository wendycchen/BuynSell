package com.cgi.accountservice.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
