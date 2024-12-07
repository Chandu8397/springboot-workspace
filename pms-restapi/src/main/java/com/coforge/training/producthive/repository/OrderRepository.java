package com.coforge.training.producthive.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coforge.training.producthive.model.Order;

/*
*Author :Mekapothula.Reddy
*Date   :26 Nov 2024
*Time   :3:39:11 pm
*
*/

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	/*
	 * @Query Annotation:

		The @Query annotation defines a custom query. SELECT o FROM Order o LEFT JOIN FETCH 
		o.orderDetailsList ensures that all Order entities are fetched along 
		with their associated OrderDetails.
		LEFT JOIN FETCH forces the fetching of the OrderDetails as part of the 
		initial query, avoiding lazy loading issues.
		
		findAllWithDetails(Pageable pageable) Method:

		This method returns a Page<Order> where each Order has its 
		OrderDetails eagerly loaded.
	 */
	
	@Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderDetailsList")
	Page<Order> findAllWithDetails(Pageable pageable);

}
