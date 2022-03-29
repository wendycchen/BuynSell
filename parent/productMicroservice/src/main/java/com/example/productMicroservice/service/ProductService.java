package com.example.productMicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productMicroservice.exception.ProductNotFoundException;
import com.example.productMicroservice.model.Product;
import com.example.productMicroservice.repository.ProductRepo;

@Service
public class ProductService {
	
	private final ProductRepo productRepo;
	
	public ProductService(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	public List<Product> getProducts() {
		return productRepo.findAll();
	}
	
	public Product getProductById(int pid) throws ProductNotFoundException {
		return productRepo.findById(pid).orElseThrow(() ->
			new ProductNotFoundException("Product doesn't exists"));
	}
	
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}
	
	public Product updateProduct(int pid, Product product) throws ProductNotFoundException {
		Product prodObj = getProductById(pid);
		prodObj.setPname(product.getPname());
		prodObj.setPrice(product.getPrice());
		prodObj.setCondition(product.getCondition());
		prodObj.setDesc(product.getDesc());
		prodObj.setPostedBy(product.getPostedBy());
		prodObj.setBrand(product.getBrand());
		prodObj.setDate(product.getDate());
		return productRepo.save(prodObj);
	}
	
	public void deleteProduct(int prodId) {
		productRepo.deleteById(prodId);
	}


}
