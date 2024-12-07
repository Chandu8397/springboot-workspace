package com.coforge.training.studentsoft.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
*Author :Mekapothula.Reddy
*Date   :22 Nov 2024
*Time   :2:47:30 pm
*
*/

/*
 * Lombok is an open-source library (basically a standalone jar) capable of 
 * doing magic in automating the boilerplate code generation for any java class. 
 *  So if Lombok is in the classpath, it can quickly get rid of all the getters & setters 
 *  methods, class constructors, hashcode and equals methods and many more 
 *  by adding a couple of annotations to the class. 
 *  Integrate Lombok to STS . Help --> Install new Software and add following url
 *  
 *  https://projectlombok.org/p2
*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
	
	private int rollNo;
    private String firstName;
    private String lastName;
    private String gender;
    private String course;
    
    

}
