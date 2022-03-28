package com.cgi.emailservice.rabbitmq;

import com.cgi.emailservice.models.EmailRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.Notification;

@Component
public class EmailServiceConsumer {

    private final EmailService emailService;

    @Autowired
    public EmailServiceConsumer(EmailService emailService){
        this.emailService = emailService;
    }
//Consumer used for email registration
    @RabbitListener(queues = )
    public void consumer(EmailRequest emailRequest){

    }
}
