package com.coforge.training.studentsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/* 
 * Spring Boot Application to perform CRUD operations
 *  on Student Objects using JDBCTemplate
 */
@SpringBootApplication
public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

}
