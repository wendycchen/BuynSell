package com.cgi.model;

import lombok.RequiredArgsConstructor;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "order_item")
@RequiredArgsConstructor
public class Orders {
	@Id
	private int orderId;
	private String orderNumber;
	@OneToMany(cascade=CascadeType.ALL)
	private List<OrderItems> orderItems;
	

	public Orders(int orderId, String orderNumber, List<OrderItems> orderItems) {
		super();
		this.orderId = orderId;
		this.orderNumber = orderNumber;
		this.orderItems = orderItems;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public List<OrderItems> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	};

	
	
	
	
	
	
}
