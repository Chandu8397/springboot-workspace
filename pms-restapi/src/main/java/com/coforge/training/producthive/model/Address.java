package com.coforge.training.producthive.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/*
*Author :Mekapothula.Reddy
*Date   :25 Nov 2024
*Time   :1:04:04 pm
*
*/

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)    //
	private Long addressId;
	
	private @NonNull String street;
	private @NonNull String city;
	private int pincode;
	
	
	@OneToOne						//One-One Mapping
	@JoinColumn(name="dealer_id")   //ForeignKey Field
	private Dealer dealer;			//Reference Class Object(Dealer)


	public Address(@NonNull String street, @NonNull String city, int pincode) {
		super();
		this.street = street;
		this.city = city;
		this.pincode = pincode;
	}

}
