package com.cgi.ampqservice.config;


import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component @AllArgsConstructor
public class RabbitMQMessageProducer {

    private AmqpTemplate amqpTemplate;

    public void publish(Object payload, String exchange, String routingKey){
        amqpTemplate.convertAndSend(exchange,routingKey,payload);
    }
}
