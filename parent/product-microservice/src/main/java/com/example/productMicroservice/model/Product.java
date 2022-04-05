package com.example.productMicroservice.model;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {

	@Id
	private int prodId;
	private double price;
	private String pname, condition, brand, desc, postedBy, imageUrl, category;
	private LocalDate date;
		
	public Product() {
	}

	public Product(int prodId, String email, String username, double price, String pname, String condition, String brand, String desc, String postedBy, String imageUrl, String category, LocalDate date) {
		this.prodId = prodId;
		this.price = price;
		this.pname = pname;
		this.condition = condition;
		this.brand = brand;
		this.desc = desc;
		this.postedBy = postedBy;
		this.imageUrl = imageUrl;
		this.category = category;
		this.date = date;
	}


	public int getprodId() {
		return prodId;
	}
	
	public void setprodId(int prodId) {
		this.prodId = prodId;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getPname() {
		return pname;
	}
	
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getPostedBy() {
		return postedBy;
	}
	
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", price=" + price + ", pname=" + pname + ", condition=" + condition
				+ ", brand=" + brand + ", desc=" + desc + ", postedBy=" + postedBy + ", imageUrl=" + imageUrl
				+ ", date=" + date + "]";
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
