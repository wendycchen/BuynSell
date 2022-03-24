package com.example.ProducerRabbitMQ.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.ProducerRabbitMQ.model.Product;

@Service
public class ProductRabbitSender {

	RabbitTemplate rabTemp;
	
	@Autowired
	public ProductRabbitSender(RabbitTemplate rabbit) {
		this.rabTemp = rabbit;
	}
	
	@Value("${spring.rabbitmq.exchange}")
	String exchange;
	
	@Value("${spring.rabbitmq.routingkey}")
	String routingkey;
	
	public void send(Product product) {
		rabTemp.convertAndSend(exchange, routingkey, product);
	}
}
