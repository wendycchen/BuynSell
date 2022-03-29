package com.cgi.emailservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Confirmation {
    private String name;
    private String email;
    private String token;
}
