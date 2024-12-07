package com.coforge.training.producthive.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*
*Author :Mekapothula.Reddy
*Date   :26 Nov 2024
*Time   :3:17:04 pm
*
*/
@Entity
@Table(name = "order_details")
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String productName;
	private Integer quantity;
	private Double price;


/*
 * The @ManyToOne annotation establishes the many-to-one relationship with Order.
  fetch = FetchType.LAZY means that the associated Order is loaded on-demand.
 */
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "order_id")
	@JsonBackReference
	private Order order;

	public OrderDetails() {
		
	}
	public OrderDetails(String productName, Integer quantity, Double price) {
	super();
	this.productName = productName;
	this.quantity = quantity;
	this.price = price;
}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}





