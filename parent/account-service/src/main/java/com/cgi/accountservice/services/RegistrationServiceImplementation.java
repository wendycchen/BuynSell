package com.cgi.accountservice.services;

import com.cgi.accountservice.exceptions.*;
import com.cgi.accountservice.models.*;
import com.cgi.ampqservice.config.RabbitMQMessageProducer;
import com.cgi.ampqservice.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service @Slf4j
public class RegistrationServiceImplementation implements RegistrationService {


    private final UserService userService;
    private final ConfirmationTokenService tokenService;
    private final RabbitMQMessageProducer producer;

    @Autowired
    public RegistrationServiceImplementation(UserService userService, ConfirmationTokenService tokenService, RabbitMQMessageProducer producer) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.producer = producer;
    }

    @Override
    public void register(RegistrationRequest regRequest) {
        String token;
        try {
            token = userService.addUser(new User(Role.USER, regRequest.getUsername(), regRequest.getFirstName(), regRequest.getLastName(), regRequest.getEmail(), regRequest.getPassword()));
            UserDto userDto = new UserDto(regRequest.getFirstName(),"thomasskiff@outlook.com",token);
            producer.publish(userDto,"internal.exchange","internal.confirmation.routing-key");
        } catch (EmailAndUsernameExists | EmailAlreadyExistsException | UsernameAlreadyExistsException e) {
            log.info("Unsuccessful Registration for user with email: {}, username: {}, firstname: {}, lastname: {}, \n with error {}",regRequest.getEmail(),regRequest.getUsername(),regRequest.getFirstName(),regRequest.getLastName(),e.getMessage());
        }

        //TODO change from my email


    }

    @Transactional
    @Override
    public void confirmEmailToken(String token) throws TokenNotFoundException, EmailAlreadyConfirmedException, TokenExpiredException {
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
        userService.enableUser(confirmationToken.getUser().getEmail());
    }

}
