package com.cgi.accountservice.services;

import com.cgi.accountservice.exceptions.EmailAlreadyConfirmedException;
import com.cgi.accountservice.exceptions.TokenExpiredException;
import com.cgi.accountservice.exceptions.TokenNotFoundException;
import com.cgi.accountservice.models.RegistrationRequest;

public interface RegistrationService {
    String register(RegistrationRequest regReq);
    String confirmToken(String token) throws TokenExpiredException, TokenNotFoundException, EmailAlreadyConfirmedException;
}