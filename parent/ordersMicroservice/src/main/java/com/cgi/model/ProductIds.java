package com.cgi.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ProductIds {

	@Id
	private int productId;
	private String productName;
	
	public ProductIds() {
		super();
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
	
}
