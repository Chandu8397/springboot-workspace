package com.coforge.training.studentsoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coforge.training.studentsoft.model.Student;
import com.coforge.training.studentsoft.service.StudentService;


/*
*Author :Mekapothula.Reddy
*Date   :22 Nov 2024
*Time   :12:48:49 pm
*/
/*
 * The @Controller annotation in Spring is used to indicate that an application 
 * component is a web request handler, such as a controller class or method. 
 * This annotation is typically used in combination with other annotations, 
 * such as @RequestMapping, to specify the URL(s) to which the controller should 
 * respond. 
 * The @Controller annotation is used to enable annotation-based configuration for 
 * controllers, allowing developers to define request handling methods and specify 
 * the HTTP methods they respond to. 
 * It is a key part of the Spring Framework's web application architecture.
*/
@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	//Client --> FC -->StudentController -->index.jsp
	//Open Browser - http://localhost:8084/studentsoft/
	@GetMapping("/")
	public String showHomePage() {
		return "index";
		
	}
	
	@GetMapping("/contact")
	public String showContactUs() {
		return "contactus";
	}
	
	/* http://localhost:8084/studentsoft/contact */
	// Program Flow - Client Request --> FC --> UserController --> Model --> View
	@GetMapping("/new")
	public String displayStudentAddForm(Model m) {
		Student student=new Student();
		m.addAttribute(student);
		return "student-form"; //returns view + student Object
	}

	//Program Flow
	//Client Request-->FC-->User Controller-->Service-->DAO Layer --> Database(MySql)
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.save(student);					//Invoke save() of service layer
		return "redirect:/students";					//redirect to students route
		
	}
	
	@GetMapping("/students")
	public String listStudents(Model model) {
		List<Student> students = studentService.getAllStudents();
		model.addAttribute("stud",students);
		return "student-list";   //view + students ArrayList
	}
	
	/*
	 * @PathVariable is an annotation used to inject the values of URI template variables 
	 * into method parameters. 
	 * It is part of the Spring MVC framework and is used to access parameters within a URL.
	 * 
	 * Model represents the data that is passed from a controller to a view. 
	 * It's essentially a container for the information that needs to be displayed to the user.
	 */
	@GetMapping("/edit/{rollNo}")
	public String updateStudentForm(@PathVariable int rollNo,Model model) {
		Student student=studentService.getSingleStudent(rollNo); //return student matching rollno
		model.addAttribute("student",student);
		return "student-form"; //returns view + student Object
	}
	
	/*
	 * Method to Update Student Details
	 * 
	 * The @ModelAttribute annotation in Spring Boot is used to automatically populate a model object 
	 * with data. 
	 * It binds the incoming request parameters or form data to a JavaBean or method argument. 
	 * This annotation is used to simplify the processing of HTTP requests by allowing you to inject 
	 * the required data into your models. 
	 */
	@PostMapping("students/{rollNo}")
	public String updateStudent(@PathVariable int rollNo,@ModelAttribute("student") Student student) {
		student.setRollNo(rollNo);
		studentService.update(student);
		return "redirect:/students"; //redirect to students route	
	}
	
	@GetMapping("/delete/{rollNo}")
	public String deleteStudent(@PathVariable int rollNo) {
		studentService.delete(rollNo);
		return "redirect:/students"; //redirect to students route	
	}
	
	@GetMapping("/shop")
	public String eShopping() {
		return "shopping";
		
	}
}
