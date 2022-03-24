package com.example.ConsumerRabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRabbitservice implements RabbitListenerConfigurer{

	@RabbitListener(queues="${spring.rabbitmq.queue}")
	public void receiveMessage(Consumer consumer) {
		System.out.println("Received message is " + consumer);
	}
	
	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		// TODO Auto-generated method stub
		
	}

}
