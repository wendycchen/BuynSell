package com.example.productMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productMicroservice.exception.ProductNotFoundException;
import com.example.productMicroservice.model.Product;
import com.example.productMicroservice.service.ProductService;

@RestController
@RequestMapping("/productMicro")
public class ProductController {

	private final ProductService productserve;
	
	public ProductController(ProductService productserve) {
		this.productserve = productserve;
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		return new ResponseEntity<>(productserve.getProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/products/{prodId}")
	public ResponseEntity<Product> getProductById(@PathVariable("prodId") int prodId) throws ProductNotFoundException{
		return new ResponseEntity<>(productserve.getProductById(prodId), HttpStatus.OK);
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		return new ResponseEntity<>(productserve.addProduct(product), HttpStatus.OK);
	}
	
	@PutMapping("/products/{prodId}")
	public ResponseEntity<Product> updateProduct(@PathVariable("prodId") int prodId, @RequestBody Product product) throws ProductNotFoundException{
		return new ResponseEntity<>(productserve.updateProduct(prodId, product), HttpStatus.OK);
	}
	
	@Transactional
	@DeleteMapping("/products/{prodId}")
	public ResponseEntity<?> deleteProduct(@PathVariable("prodId") int prodId){
		productserve.deleteProduct(prodId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
