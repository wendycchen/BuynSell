package com.example.ProducerRabbitMQ;



import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MessageConverter;

import com.rabbitmq.client.ConnectionFactory;

@SpringBootApplication
public class ProducerRabbitMqApplication {

	@Value("${spring.rabbitmq.host}")
	String host;
	
	@Value("${spring.rabbitmq.username}")
	String username;
	
	@Value("${spring.rabbitmq.password}")
	String password;
	
	public static void main(String[] args) {
		SpringApplication.run(ProducerRabbitMqApplication.class, args);
	}
	
	@Bean
	CachingConnectionFactory getConnection() {
		CachingConnectionFactory cachFact = new CachingConnectionFactory(host);
		cachFact.setUsername(username);
		cachFact.setPassword(password);
		return cachFact;
	}

	@Bean
	public Jackson2JsonMessageConverter jsonMessageconverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate getTemplate(ConnectionFactory conFact) {
		RabbitTemplate rabTemp = new RabbitTemplate();
		rabTemp.setMessageConverter(jsonMessageconverter());
		return rabTemp;
	}
}
