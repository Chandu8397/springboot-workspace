package com.coforge.training.producthive.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
*Author :Mekapothula.Reddy
*Date   :23 Nov 2024
*Time   :12:44:48 pm
*
*/
/*
 * Model class for Managing products using Hibernate - JPA
 * The @Entity annotation specifies that the class is an entity and is 
 * mapped to a database table.
*/
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data


public class Product {
	
	/*
	 * The @Id annotation specifies the primary key of an entity.
	 * @GeneratedValue provides for the specification of generation strategies for the values of primary keys.
	 * @SequenceGenerator, you can specify the sequence name, initial value, and allocation size for the sequence. 
	 * This allows you to control the sequence generation process and ensure database consistency. 
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "prod_seq")
	@SequenceGenerator(name="prod_seq",initialValue = 1000,allocationSize=1)
	private Long pid;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String brand;
	
	@Column(nullable = false)
	private String madein;
	
	@Column(nullable = false)
	private float price;
	
	
	
	
	

}
