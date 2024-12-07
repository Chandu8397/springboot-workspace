package com.coforge.training.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	//Open Browser - http://localhost:8080/
	@GetMapping("/")    //GET Request
	public String sayHello() {
		return "Hello World from Spring Boot";
	}
	
	//Open Browser - http://localhost:8080/chandu

	@GetMapping("/chandu")
	public String test() {
		return "Myself Chandu, created my first Spring Boot App in Coforge Training";
	}
	
	//Open Browser - http://localhost:8080/city
	@GetMapping("/city")
	public String displayCity() {
		return "Iam from Hyderabad";
	}
	
	//Open Browser - http://localhost:8080/training
	@GetMapping("/training")
	public String Training() {
		return "Iam in Coforge Training, Learning SpringBoot";
	}
}
