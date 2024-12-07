package com.coforge.training.producthive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *Author :Mekapothula.Reddy
 *Date   :26 Nov 2024
 *Time   :2:37:40 pm
 *
 */

//Model class to perform Inner Join Dealer & Address classes

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DealerAndAddressProjection {

	private Long id;
	private String fname;
	private String lname;
	private String phoneNo;
	private String email;
	private String street;
	private String city;
	private  int pincode;

}
