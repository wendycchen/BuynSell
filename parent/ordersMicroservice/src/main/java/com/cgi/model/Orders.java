package com.cgi.model;
import javax.persistence.Id;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;



@Entity
public class Orders {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private long orderNumber;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List <ProductIds> productId;

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

	public List<ProductIds> getProductId() {
		return productId;
	}

	public void setProductId(List<ProductIds> productId) {
		this.productId = productId;
	}
}
