package com.cgi.accountservice.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//TODO
@RestController
@RequestMapping(value = "/api/v1/")
public class ProducerController {
    private RabbitSenderService rabbitSenderService;
    @Autowired
    public ProducerController(RabbitSenderService rabbitSenderService) {
        this.rabbitSenderService = rabbitSenderService;
    }

    @PostMapping(value = "user")
    public RequestEntity<?> publishUserDetails() {
        rabbitSenderService.sendToEmailService();
        return null;
    }
}