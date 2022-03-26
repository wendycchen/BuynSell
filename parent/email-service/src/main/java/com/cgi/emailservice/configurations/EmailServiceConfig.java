package com.cgi.emailservice.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailServiceConfig {

    //TODO queue, routing
    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;


}
