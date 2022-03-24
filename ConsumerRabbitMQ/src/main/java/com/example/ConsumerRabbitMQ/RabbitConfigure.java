package com.example.ConsumerRabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class RabbitConfigure {

	
	@Value("${spring.rabbitmq.queue}")
	String queue;
	
	@Value("${spring.rabbitmq.exchange}")
	String exchange;
	
	@Value("${spring.rabbitmq.routingkey}")
	String routekey;
	
	@Value("${spring.rabbitmq.username}")
	String username;
	
	@Value("${spring.rabbitmq.password}")
	String password;
	
	@Value("${spring.rabbitmq.host}")
	String host;
	
	@Bean
	Queue getqueue() {
		return new Queue(queue, true);
	}
	
	@Bean
	Exchange getexchange() {
		return ExchangeBuilder.directExchange(exchange).durable(true).build();
	}
	
	@Bean
	Binding binding() {
		return BindingBuilder.bind(getqueue()).to(getexchange()).with(routekey).noargs();
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
