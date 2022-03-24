package com.example.ConsumerRabbitMQ;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ConsumerRabbitMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerRabbitMqApplication.class, args);
	}

}
