package com.cgi.accountservice.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration @AllArgsConstructor @RequiredArgsConstructor
public class RabbitConfig {
    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queue.confirmation}")
    private String confirmationQueue;

    //@Value("$rabbitmq.routing-keys.internal-confirmation")
    private String internalConfirmationRoutingKey ="internal.confirmation.routing-key";

    @Bean
    public Queue confirmationQueue(){
        return new Queue(this.confirmationQueue);
    }

    @Bean
    public TopicExchange internalTopicExchange(){
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Binding internalConfirmationBinding(){
        return BindingBuilder.bind(confirmationQueue()).to(internalTopicExchange()).with(this.internalConfirmationRoutingKey);
    }


    public String getInternalExchange(){
        return internalExchange;
    }

    public String getConfirmationQueue(){
        return confirmationQueue;
    }

    public String getInternalConfirmationRouting(){
        return internalConfirmationRoutingKey;
    }
}