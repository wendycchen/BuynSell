package com.cgi.accountservice.models;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.sun.xml.bind.v2.TODO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity @Getter @Setter @NoArgsConstructor @ToString
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role userRole = Role.USER;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean isEnabled = false;

    //TODO add other fields

    public User(Role userRole, String username, String firstName, String lastName, String email, String password) {
        this.userRole = userRole;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

}
