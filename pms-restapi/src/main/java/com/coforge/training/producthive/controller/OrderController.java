package com.coforge.training.producthive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.training.producthive.model.Order;
import com.coforge.training.producthive.service.OrderService;

/*
 *Author :Mekapothula.Reddy
 *Date   :26 Nov 2024
 *Time   :3:46:31 pm
 *
 */

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderService orderService;

	/* Open PostMan, make a POST Request http://localhost:8088/product-hive/api/orders
	 * Select body -> raw -> JSON 
	 * Insert JSON order object.
	 * {
  "orderNumber": "ORD12347",
  "orderDetailsList": [
    {"productName": "PenDrive", "quantity": 100, "price": 600.0},
    {"productName": "KeyBoard", "quantity": 25, "price": 250.0},
    {"productName": "Mouse", "quantity": 150, "price": 600.0},
    {"productName": "Monitor", "quantity": 120, "price": 3000.0},
    {"productName": "Speakers", "quantity": 400, "price": 1500.0}
  ]
}
	 */
	@PostMapping("/orders")
	public ResponseEntity<Order> createOrder(@RequestBody Order order){
		try {
			Order savedOrder=orderService.createOrder(order);

			return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@GetMapping("/orders")
	public ResponseEntity<Page<Order>> getAllOrders(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		Page<Order> orders = orderService.getAllOrders(pageable);
		return ResponseEntity.ok(orders);
		
	}
}
