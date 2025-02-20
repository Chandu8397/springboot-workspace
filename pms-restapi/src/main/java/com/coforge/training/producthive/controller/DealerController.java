package com.coforge.training.producthive.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.training.producthive.exception.ResourceNotFoundException;
import com.coforge.training.producthive.model.Address;
import com.coforge.training.producthive.model.Dealer;
import com.coforge.training.producthive.model.DealerAndAddressProjection;
import com.coforge.training.producthive.service.DealerService;

/*
*Author :Mekapothula.Reddy
*Date   :25 Nov 2024
*Time   :3:29:49 pm
*
*Controller for Registaration & Login process of Dealer
*/

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins = "http://localhost:4200")
public class DealerController {
	
	private final DealerService dservice;

	//DI Using Constructors
	public DealerController(DealerService dservice) {
		super();
		this.dservice = dservice;
	}
	
	
	//Open PostMan, make a POST Request - http://localhost:8088/product-hive/api/register
	//Select body -> raw -> JSON 
	//Insert JSON Dealer object.
	@PostMapping("/register")
	public ResponseEntity<String> createDealer(@Validated @RequestBody Dealer dealer){
		try {
			Address address = dealer.getAddress();    //get Data from Secondary table
			
			//Establish bi-directional Mapping
			address.setDealer(dealer);
			dealer.setAddress(address);
			
			Dealer registeredDealer = dservice.registerDealer(dealer); //save Dealer details
			if(registeredDealer != null) {
				return ResponseEntity.ok("Registration Successfull");
			} else {
				return ResponseEntity.badRequest().body("Registartion Failed");
			}
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
					body("An Error Occured :"+e.getMessage());
		}
		
	}
	
	
	//Open PostMan, make a POST Request - http://localhost:8088/product-hive/api/login
	//Select body -> raw -> JSON 
	//Insert JSON Dealer object with email & password.
	@PostMapping("/login")
	public ResponseEntity<Boolean> loginDealer(@Validated @RequestBody Dealer dealer)
		throws ResourceNotFoundException
	{
		Boolean isLogin = false;
		String email = dealer.getEmail();
		String password = dealer.getPassword();
		
		Dealer d = dservice.loginDealer(email).orElseThrow(() ->		//Invokes LoginDealer method with email parameter
		new ResourceNotFoundException("Dealer doesn't Exist ::"+email));
		
		if(email.equals(d.getEmail()) && password.equals(d.getPassword())){
			isLogin=true;
		}
		return ResponseEntity.ok(isLogin);
		
	}
	
	@GetMapping("/dealers")
	public ResponseEntity<List<DealerAndAddressProjection>> getDealerInfo(){
		try {
			List<DealerAndAddressProjection> selectedFields = dservice.getDealerInfo();
			
			return ResponseEntity.ok(selectedFields);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	
	}

	                          
}
