package com.cgi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgi.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

}
