package com.cgi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cgi.exception.OrderNotFoundException;
import com.cgi.model.Orders;
import com.cgi.repository.OrderRepository;
@Service
public class OrderService {
	
	private OrderRepository repo;
	
	public OrderService(OrderRepository repo) {
		this.repo = repo;
	}
	
	public Orders addOrder(Orders order) {
	// save new order
	return repo.save(order);
	}
	
	public Orders getByOrderId(int orderId) throws OrderNotFoundException{
		//get the productID to pull the Items in order
		if(repo.existsById(orderId)) {
			Orders prodList = repo.findById(orderId).get();
			return prodList;
		}else {
			throw new OrderNotFoundException("Order does not exist");
		}
	}
	// admin can check all orders? 
	public List<Orders> getOrders(){
		return repo.findAll();
	}
	// optional service ?
	public void cancelOrder(int orderId) {
		repo.deleteById(orderId);
	}
	
	//Search by orderNUMBER
	public Optional<Orders> getProductByOrderNumber(long orderNumber) throws OrderNotFoundException{
		if(repo.findByOrderNumber(orderNumber).isEmpty()) {
			throw new OrderNotFoundException("Order does not exist");
		}else {
			return repo.findByOrderNumber(orderNumber);
		}
	}
	
	
	
}
