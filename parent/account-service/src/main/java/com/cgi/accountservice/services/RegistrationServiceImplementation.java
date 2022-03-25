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
public class RegistrationServiceImplementation implements RegistrationService{

    private EmailValidationService emailValidator;
    private UserService userService;
    private ConfirmationTokenService tokenService;

    public RegistrationServiceImplementation(EmailValidationService emailValidator, UserService userService, ConfirmationTokenService tokenService){
        this.emailValidator = emailValidator;
        this.userService = userService;
        this.tokenService = tokenService;
    }


    @Override
    public String register(RegistrationRequest regRequest) throws EmailNotValidException {

        boolean isValidEmail = emailValidator.test(regRequest.getEmail());
        if(!isValidEmail) {
            throw new EmailNotValidException("Email is not valid");
        }

        String token;
        try {
            token = userService.registerUser(new User(Role.USER,
                    regRequest.getUsername(),
                    regRequest.getFirstName(),
                    regRequest.getLastName(),
                    regRequest.getEmail(),
                    regRequest.getPassword()
            ));

        } catch (EmailAndUsernameExists | EmailAlreadyExistsException | UsernameAlreadyExistsException e) {
            return e.getMessage();
        }

        //TODO find a way to send to email service
        //String link = "http://localhost:9004/api/register/confirm?token=";
        //emailSender.send(regRequest.getEmail(), buildEmail(regRequest.getFirstName(), link+token));
        return token;
    }
    @Transactional @Override
    public String confirmToken(String token) throws TokenNotFoundException, EmailAlreadyConfirmedException, TokenExpiredException{
        ConfirmationToken confirmationToken = tokenService
                .getToken(token)
                .orElseThrow(() -> new TokenNotFoundException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new EmailAlreadyConfirmedException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("token expired");
        }

        tokenService.setConfirmedAt(token);
        userService.enableAppUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }

}
