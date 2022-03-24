package com.example.ProducerRabbitMQ.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id", scope=Product.class)
public class Product implements Serializable{

	private String id, productname;

	public Product(String id, String productname) {
		super();
		this.id = id;
		this.productname = productname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productname=" + productname + "]";
	}
	
}
