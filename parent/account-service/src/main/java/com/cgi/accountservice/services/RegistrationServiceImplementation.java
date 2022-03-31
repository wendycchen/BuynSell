package com.cgi.accountservice.services;

import com.cgi.accountservice.exceptions.*;
import com.cgi.accountservice.models.ConfirmationToken;
import com.cgi.accountservice.models.RegistrationRequest;
import com.cgi.accountservice.models.Role;
import com.cgi.accountservice.models.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class RegistrationServiceImplementation implements RegistrationService {


    private final UserService userService;
    private final ConfirmationTokenService tokenService;

    public RegistrationServiceImplementation(UserService userService, ConfirmationTokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Override
    public String register(RegistrationRequest regRequest) {
        String token;
        try {
            token = userService.addUser(new User(Role.USER, regRequest.getUsername(), regRequest.getFirstName(), regRequest.getLastName(), regRequest.getEmail(), regRequest.getPassword()));

        } catch (EmailAndUsernameExists | EmailAlreadyExistsException | UsernameAlreadyExistsException e) {
            return e.getMessage();
        }

        //TODO find a way to send to email service
        //String link = "http://localhost:9004/api/register/confirm?token=";
        //emailSender.send(regRequest.getEmail(), buildEmail(regRequest.getFirstName(), link+token));
        return regRequest.getEmail();
    }

    @Transactional
    @Override
    public String confirmToken(String token) throws TokenNotFoundException, EmailAlreadyConfirmedException, TokenExpiredException {
        ConfirmationToken confirmationToken = tokenService.getToken(token).orElseThrow(() -> new TokenNotFoundException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new EmailAlreadyConfirmedException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("token expired");
        }

        tokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
        return "confirmed";
    }

}