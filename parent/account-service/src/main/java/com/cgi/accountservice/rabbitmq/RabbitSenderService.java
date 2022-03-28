package com.cgi.accountservice.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//TODO implement rabbit
@Service
public class RabbitSenderService {
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public void sendToEmailService() {
        rabbitTemplate.convertAndSend(exchange, routingkey, "hi");
    }

}