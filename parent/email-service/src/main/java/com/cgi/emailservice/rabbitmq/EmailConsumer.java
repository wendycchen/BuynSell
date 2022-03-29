package com.cgi.emailservice.rabbitmq;

import com.cgi.emailservice.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailConsumer {

    private final EmailService emailService;

    @Autowired
    public EmailConsumer(EmailService emailService){
        this.emailService = emailService;
    }

    //TODO finish this
}
