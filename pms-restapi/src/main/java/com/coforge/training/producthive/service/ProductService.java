package com.coforge.training.producthive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coforge.training.producthive.model.Product;
import com.coforge.training.producthive.repository.ProductRepository;

/*
*Author :Mekapothula.Reddy
*Date   :23 Nov 2024
*Time   :3:07:54 pm
*
*/
/*
 * @Service annotation is a stereotype annotation that marks a class as a service layer component. 
 * It's one of the core annotations used to structure your application and enable dependency injection.
 * 
 * The @Service annotation tells Spring that the annotated class contains business logic. 
 * It's typically where you'll implement the core functionality of your application, 
 * such as calculations, data retrieval, or external API interactions.
 */

@Service
public class ProductService {
	
	private final ProductRepository prepo;
	
	//Constructor Injection
	public ProductService(ProductRepository prepo) {
		this.prepo = prepo;
	}
	
	public Product saveProduct(Product p) {
		return prepo.save(p);			//Invokes pre-defined method save() of JPA repository
	}
	
	
	public List<Product> listAll() {
		return prepo.findAll();			//Invokes pre-defined method findAll() of JPA repository

		
	}
	
	//Optional return type is to handle Null Pointer Exception
	public Optional<Product> getSingleProduct(long pid) {
		return prepo.findById(pid);		//Invokes pre-defined method findBy() of JPA repository

		
	}
	
	public void deleteProduct(long pid) {
		prepo.deleteById(pid);    //Invokes pre-defined method deleteById() of JPA repository
		
	}
	
	public List<Product> searchProductByName(String name) {
		return prepo.findProductByNameContainingIgnoreCase(name);
		
	}

}
