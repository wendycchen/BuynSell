package com.cgi.ampqservice.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageProducer {

    private AmqpTemplate amqpTemplate;

    @Autowired
    public RabbitMQMessageProducer(AmqpTemplate amqpTemplate){
        this.amqpTemplate = amqpTemplate;
    }

    public void publish(Object payload, String exchange, String routingKey){
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
    }
}
