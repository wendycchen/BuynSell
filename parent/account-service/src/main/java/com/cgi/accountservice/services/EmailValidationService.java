package com.cgi.accountservice.services;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
@Service
public class EmailValidationService implements Predicate<String> {
    //Testing to see if email matches REGEX
    @Override
    public boolean test(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return email.matches(regex);
    }
}
