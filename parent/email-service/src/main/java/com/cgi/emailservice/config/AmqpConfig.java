package com.cgi.emailservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbit.queues.confirmation}")
    private String confirmationQueue;

    @Value("$rabbitmq.routing-keys.internal-confirmation")
    private String internalConfimationRoutingKey;

    @Bean
    public Queue confirmationQueue(){
        return new Queue(this.confirmationQueue);
    }

    @Bean
    public Binding internalToNotificationBinding(){
        return BindingBuilder.bind(confirmationQueue()).to(internalTopicExchange()).with(this.internalConfimationRoutingKey);
    }

    @Bean
    public TopicExchange internalTopicExchange(){
        return new TopicExchange(this.internalExchange);
    }

    public String getInternalExchange(){
        return internalExchange;
    }

    public String getConfirmationQueue(){
        return confirmationQueue;
    }

    public String getInternalConfimationRoutingKey(){
        return internalConfimationRoutingKey;
    }
}
