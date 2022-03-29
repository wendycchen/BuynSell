package com.cgi.emailservice.rabbitmq;

import com.cgi.ampqservice.model.UserDto;
import com.cgi.emailservice.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component @Slf4j
public class EmailConsumer {

    private final EmailService emailService;

    @Autowired
    public EmailConsumer(EmailService emailService){
        this.emailService = emailService;
    }

    //TODO finish this
    @RabbitListener(queues = "confirmation.queue")
    public void consumer(UserDto userInfo){
        //TODO add logger
        log.info("made it");
        String link = "http://localhost:9006/api/v1/account/confirm?token=" + userInfo.token();
        String email = emailService.buildConfirmationEmail(userInfo.name(),link);
        emailService.sendConfirmation(userInfo.email(), email);
    }
}
