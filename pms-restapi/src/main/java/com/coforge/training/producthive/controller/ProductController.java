package com.coforge.training.producthive.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.training.producthive.exception.ResourceNotFoundException;
import com.coforge.training.producthive.model.Product;
import com.coforge.training.producthive.service.ProductService;

/*
*Author :Mekapothula.Reddy
*Date   :23 Nov 2024
*Time   :12:38:09 pm
*/
/*Spring RestController annotation is used to create RESTful web services using Spring MVC. 
 * Spring RestController takes care of mapping request data to the defined request handler method. 
 * Once response body is generated from the handler method, it converts it to JSON or XML response.
 *  
 * @RestController indicates that this class handles HTTP requests and automatically 
 * serializes the results to JSON.
 * 
 * @RequestMapping - maps HTTP request with a path to a controller 
 */

@RestController
@RequestMapping(value="/api") 
@CrossOrigin(origins = "http://localhost:4200")
//Base URL for this controllers endpoints
public class ProductController {
	
	@Autowired
	private ProductService pservice;
		
	@GetMapping("/")
	public String welcome() {
		return "welcome to Product Management System";
	}
	
	/*
	 * In Spring Boot, a Response Entity is a class that helps in returning a response to a HTTP request. 
	 * It's part of the Spring Framework and is used to simplify the process of returning HTTP responses. 
	 * A ResponseEntity typically includes the HTTP status code, headers, and a body. 
	 * This can be a JSON or XML response, or even a simple string. 
	 * ResponseEntity is a powerful and flexible way to return responses from a Spring Boot application, 
	 * making it easy to manage HTTP communication and handle errors.
	 * 
	 * @RequestBody annotation automatically deserializes the JSON into a Java type
	 * 
	 * @Validated annotation is a tool that helps validate the data being passed to a controller method. 
	 */

	//Open PostMan, make a POST Request - http://localhost:8088/product-hive/api/products
	//Select body -> raw -> JSON 
	//Insert JSON product object.
	@PostMapping("/products")
	public ResponseEntity<Product> saveProduct(@Validated @RequestBody Product product) {
		try {
			Product p = pservice.saveProduct(product);
			return ResponseEntity.status(HttpStatus.CREATED).body(p);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	//Open PostMan/Browser, make a GET Request - http://localhost:8088/product-hive/api/products
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		try {
			List<Product> products=pservice.listAll();		//Invokes listAll() service method
			return ResponseEntity.ok(products);
			}
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	
	//Open PostMan/Browser, make a GET Request - http://localhost:8088/product-hive/api/products/1004
	@GetMapping("/products/{pid}")
	public ResponseEntity<Product> getProductById(@PathVariable(value="pid") Long pId)
		throws ResourceNotFoundException{
			
			Product p=pservice.getSingleProduct(pId).orElseThrow(() ->
					new ResourceNotFoundException("Product Not Found for this Id: "+pId));
			return ResponseEntity.ok(p);
		
	}
	
	//Open PostMan, make a PUT Request - http://localhost:8088/product-hive/api/products/1004
	//Select body -> raw -> JSON 
	//Update JSON product object with new Values.
	@PutMapping("/products/{pid}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value="pid") Long pId,
			@Validated @RequestBody Product p) throws ResourceNotFoundException{
		
		Product product = pservice.getSingleProduct(pId).orElseThrow(()
				-> new ResourceNotFoundException("Product Not Found for this Id: "+pId));
		
		//update product with new values
		product.setBrand(p.getBrand());
		product.setMadein(p.getMadein());
		product.setName(p.getName());
		product.setPrice(p.getPrice());
		
		final Product updateProduct = pservice.saveProduct(product);	//Invokes service layer method
		return ResponseEntity.ok().body(updateProduct);
	}
	
	//Open PostMan, make a DELETE Request - http://localhost:8088/product-hive/api/products/1004
	@DeleteMapping("/products/{pid}")
	public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable(value="pid") Long pId)
		 throws ResourceNotFoundException{
		
		pservice.getSingleProduct(pId).orElseThrow(()
				-> new ResourceNotFoundException("Product Not Found for this Id: "+pId));
		
		pservice.deleteProduct(pId);		//Invokes Service layer
		
		Map<String,Boolean> response = new HashMap<>();		//Map stores Data in Key-values pairs
		response.put("Deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
		
	}
	
	
	/*
	 * In Spring Boot, RequestParam is a standard annotation used to inject request parameters 
	 * from a web request into a controller method. 
	 * It is often used to extract data from HTTP requests, such as form parameters or query parameters. 
	 * When a controller method is annotated with @RequestParam, the method parameter is 
	 * bound to the value of the corresponding request parameter. 
	 * For example, @RequestParam("name") String name would bind the name method parameter to 
	 * the value of the "name" request parameter.
	 * 	
	 * ResponseEntity<?> indicates that the body of the response can be any type, 
	 * making it a generic reusable class.(<?>=type in String,Object,Class,Boolean...)
	 */
	
	// GET Request - http://localhost:8088/product-hive/api/search?name=Lap top
	@GetMapping("/search")
	public ResponseEntity<?> searchProductByName(@RequestParam("name") String name) {
		try {
			List<Product> products = pservice.searchProductByName(name);
			
			if(products.isEmpty()) {
				return new ResponseEntity<>("No Products found with given name.", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch(Exception ex) {
			return new ResponseEntity<>("An error occured while searching for Products.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	 // Client (POSTMAN/Browser) --> request --->FC --> Controller ---> Service --> Repository --> JPA --> DB(MySQL)
	 // DB - Response --> JPA --> Repository --> Service ---> Controller ---> FC ---> PostMan/Browser
		
}

