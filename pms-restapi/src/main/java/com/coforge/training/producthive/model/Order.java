package com.coforge.training.producthive.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
*Author :Mekapothula.Reddy
*Date   :26 Nov 2024
*Time   :3:12:56 pm
*Model class for storing Orders
*Implement Collection Mapping & One-Many Mapping
*/
@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String orderNumber;

	/*
	 * We have a List<OrderDetails> named orderDetailsList representing the collection of order details 
	 * associated with an order.
		The @OneToMany annotation establishes a one-to-many relationship between Order and OrderDetails.
		mappedBy = "order" indicates that the order field in OrderDetails owns the relationship.
		cascade = CascadeType.ALL ensures that all operations (persist, merge, remove, refresh, detach) 
		are cascaded to the associated OrderDetails.
		orphanRemoval = true automatically removes OrderDetails entities that are no longer referenced 
		in the orderDetailsList.
		
	 */
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonManagedReference		//to handle bi-directional mapping Forward
	private List<OrderDetails> orderDetailsList = new ArrayList<>();    //Collection Mapping
	
	public Order() {
		
	}
	
	public Order(String orderNumber) {
		super();
		this.orderNumber = orderNumber;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


	public List<OrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}


	public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}
	
	// Helper methods addOrderDetails and removeOrderDetails are provided to manage the bidirectional relationship conveniently.
    public void addOrderDetails(OrderDetails orderDetails) {
        orderDetailsList.add(orderDetails);
        orderDetails.setOrder(this); //Set relationship here
    }

    public void removeOrderDetails(OrderDetails orderDetails) {
        orderDetailsList.remove(orderDetails);
        orderDetails.setOrder(null);
    }
	

}
