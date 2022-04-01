package com.cgi.model;
import javax.persistence.Id;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Entity
public class Orders {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	Random rand = new Random();
	private long orderNumber = rand.nextLong(1000, 1000000);
	
	private String email;

	public Orders() {
		super();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
