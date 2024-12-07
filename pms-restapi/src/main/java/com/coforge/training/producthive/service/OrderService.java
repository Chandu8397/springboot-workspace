package com.coforge.training.producthive.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coforge.training.producthive.model.Order;
import com.coforge.training.producthive.model.OrderDetails;
import com.coforge.training.producthive.repository.OrderRepository;

/*
*Author :Mekapothula.Reddy
*Date   :26 Nov 2024
*Time   :3:40:40 pm
*
*/
@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orepo;
	
	public Order createOrder(Order order) {
		List<OrderDetails> detailsCopy = new ArrayList<>(order.getOrderDetailsList());
		
		for(OrderDetails details:detailsCopy) {
			order.addOrderDetails(details);
		}
		return orepo.save(order);
		
		}
	
	/*
	 *  Spring Boot provides built-in support for pagination through the use of 
	 *  its Page and Slice interfaces. 
	 *  These interfaces allow you to page through large datasets and provide features like page navigation, 
	 *  total item count, and sorting. 
	 *  By using Spring Boot's pagination features, you can efficiently manage 
	 *  and display large amounts of data in your web application, enhancing 
	 *  user experience and improving performance.
	 */
	/*
	 * Pageable is a feature in Spring Boot that allows you to implement pagination in your applications. 
	 * It enables you to split large datasets into smaller, more manageable pages,
	 *  improving the performance and usability of your APIs. 
	 *  The Pageable interface is used to define the pagination parameters, 
	 *  such as the page number and size. 
	 *  You can use it in combination with the Page object to retrieve the desired data. 
	 *  This feature is particularly useful in applications where you 
	 *   need to handle large amounts of data and provide a user-friendly experience.
	 */
	
	public Page<Order> getAllOrders(Pageable pageable) {
		Page<Order> orders = orepo.findAllWithDetails(pageable);
		return orders;
		
	}
	
}
