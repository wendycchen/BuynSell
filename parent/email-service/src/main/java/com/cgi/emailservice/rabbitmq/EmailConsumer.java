package com.cgi.emailservice.rabbitmq;

import com.cgi.emailservice.models.Confirmation;
import com.cgi.emailservice.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailConsumer {

    private final EmailService emailService;

    @Autowired
    public EmailConsumer(EmailService emailService){
        this.emailService = emailService;
    }

    //TODO finish this
    @RabbitListener(queues = "${rabbit.queues.confirmation}")
    public void confirmationConsumer(Confirmation confirmation){
        //TODO add logger
        String link = "http://localhost:9006/api/v1/confirm?token=" + confirmation.getToken();
        emailService.buildConfirmationEmail(link, confirmation.getName());
        emailService.sendConfirmation(confirmation.getName(), confirmation.getEmail());
    }
}
