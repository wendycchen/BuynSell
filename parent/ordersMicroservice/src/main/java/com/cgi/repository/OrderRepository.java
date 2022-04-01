package com.cgi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
	
	public Optional<Orders> findByOrderNumber(long orderNumber);
	
	public List<Orders> findByEmail(String email);
	
}
