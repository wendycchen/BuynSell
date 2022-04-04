package com.example.productMicroservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.productMicroservice.model.Product;

@Repository
public interface ProductRepo extends MongoRepository<Product, Integer> {

	Optional<Product> findById(int prodId);
	void deleteByProdId(int prodId);
}
