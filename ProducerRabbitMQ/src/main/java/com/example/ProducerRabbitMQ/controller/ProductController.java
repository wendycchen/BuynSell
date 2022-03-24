package com.example.ProducerRabbitMQ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProducerRabbitMQ.model.Product;
import com.example.ProducerRabbitMQ.service.ProductRabbitSender;

@RestController
@RequestMapping("/rabbit/producer")
public class ProductController {

	@Autowired
	ProductRabbitSender productserve;
	
	@Value("${app.message}")
	String message;
	
	@PostMapping("/addproduct")
	public String publishproduct(@RequestBody Product product) {
		productserve.send(product);
		return message;
	}
}
