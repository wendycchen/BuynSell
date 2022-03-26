package com.cgi.accountservice.services;


import com.cgi.accountservice.exceptions.EmailAlreadyExistsException;
import com.cgi.accountservice.exceptions.EmailAndUsernameExists;
import com.cgi.accountservice.exceptions.UsernameAlreadyExistsException;
import com.cgi.accountservice.models.User;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {
    UserDetails loadUserByUsername(String email);

    String addUser(User user) throws EmailAndUsernameExists, EmailAlreadyExistsException, UsernameAlreadyExistsException;

    void enableUser(String email);

    //TODO
}
