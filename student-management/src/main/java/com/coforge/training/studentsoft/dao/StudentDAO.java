package com.coforge.training.studentsoft.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.coforge.training.studentsoft.model.Student;
/*
*Author :Mekapothula.Reddy
*Date   :22 Nov 2024
*Time   :3:25:18 pm
*/
/*
 * In Spring Boot, the @Repository annotation is used to indicate that a class 
 * functions as a repository. 
 * Repositories are responsible for interacting with the database, 
 * handling data access logic, and providing CRUD (Create, Read, Update, Delete) operations.
 */
@Repository
public class StudentDAO {
	 
	  /*
		 * JdbcTemplate is a powerful tool in Spring Boot that simplifies database interactions. 
		 * It allows you to interact with the database using a simple, template-based model. 
		 * With JdbcTemplate, you can execute SQL statements, retrieve data, and perform CRUD operations. 
		 * Spring Boot provides an automatically configured JdbcTemplate instance, making it easy to use in 
		 * your applications. 
		 * You can inject the JdbcTemplate instance into your Spring Boot beans and use its methods 
		 * to perform database operations. 
		 * JdbcTemplate is a great choice for simplifying database integration in your Spring Boot projects.
		 */
		 //DI using Field
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// ? -placeholder symbol - values will be inserted from FORM at runtime
	public int save(Student student) {
		String sql="INSERT INTO student(firstName,lastName,gender,course) VALUES (?,?,?,?)";
		//Update() is predefined method for JdbcTemplate to insert record
		return jdbcTemplate.update(sql,student.getFirstName(),student.getLastName(),
				student.getGender(),student.getCourse());
	}
	
	public int update(Student student) {	
		String sql="UPDATE student SET firstName=?,lastName=?,gender=?,course=? WHERE rollNo=?";
		//update() is predefined method of JdbcTemplate to update Record
		return jdbcTemplate.update(sql,student.getFirstName(),student.getLastName(),
				student.getGender(),student.getCourse(),student.getRollNo()); 
	}
	
	public int delete(int rollNo) {	
		String sql="DELETE FROM student WHERE rollNo=?";
		//update() is predefined method of JdbcTemplate to delete Record
		return jdbcTemplate.update(sql,rollNo); 
	}
	
	//Method to fetch Single Record from Table
	public Student get(int rollNo) {
		String query="SELECT * FROM Student WHERE rollNo=?";
		//queryForObject() is predefined method of JdbcTemplate to query a Single record from Table 
		return jdbcTemplate.queryForObject(query,new StudentRowMapper(),rollNo);
	}
	
	//Method to fetch All Records from Table
	public List<Student> listAll(){
		String query="SELECT * FROM Student";
		//query() is predefined method of JdbcTemplate to query All records from table
		return jdbcTemplate.query(query,new StudentRowMapper());
		
	}

}
