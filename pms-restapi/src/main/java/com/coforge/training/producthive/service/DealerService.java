package com.coforge.training.producthive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.training.producthive.model.Dealer;
import com.coforge.training.producthive.model.DealerAndAddressProjection;
import com.coforge.training.producthive.repository.DealerRepository;

/*
*Author :Mekapothula.Reddy
*Date   :25 Nov 2024
*Time   :3:23:39 pm
*
*/
@Service
public class DealerService {
	
	//DI using Fields
	@Autowired
	private DealerRepository drepo;
	
	public Dealer registerDealer(Dealer d) {
		return drepo.save(d);       //Invokes pre-defined Method of JPA 
		
	}
	
	public Optional<Dealer> loginDealer(String email) {
		return drepo.findByEmail(email);      //Invokes Custom Method of JPA drepo
		
	}
	

	 public List<DealerAndAddressProjection> getDealerInfo() {
			return drepo.findSelectedFieldsFromDealerAndAddress(); //Invokes Custom Query method
		}
	
}