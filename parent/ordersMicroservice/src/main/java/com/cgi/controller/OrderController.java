package com.cgi.controller;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.exception.OrderNotFoundException;
import com.cgi.model.Orders;
import com.cgi.service.OrderService;


@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService service;
	
	public OrderController(OrderService service) {
		this.service = service;
	}

	
	@GetMapping("/allOrders")
	public ResponseEntity<List<Orders>> getAllOrders(){
		return new ResponseEntity<>(service.getOrders(), org.springframework.http.HttpStatus.OK);
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<Orders> getOrderById(@PathVariable("orderId") int orderId) throws OrderNotFoundException{
		return new ResponseEntity<>(service.getByOrderId(orderId), org.springframework.http.HttpStatus.OK);
	}
	
	@GetMapping("/orderNumber/{orderNumber}")
	public Optional<Orders> getOrderByOrderNumber(@PathVariable("orderNumber")long orderNumber) throws OrderNotFoundException{
		return service.getProductByOrderNumber(orderNumber);
	}
	
	@PostMapping("/order/newOrder")
	public ResponseEntity<Orders> addOrders(@RequestBody Orders order){
		return new ResponseEntity<>(service.addOrder(order), org.springframework.http.HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteOrders/{orderId}")
	public String deleteProduct(@PathVariable("orderId") int orderId) throws OrderNotFoundException {
		
		service.cancelOrder(orderId);
		return "Order successfully deleted";
		
	}
	
	

	
	
}
