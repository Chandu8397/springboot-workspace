package com.coforge.training.producthive.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coforge.training.producthive.model.Dealer;
import com.coforge.training.producthive.model.DealerAndAddressProjection;

/*
*Author :Mekapothula.Reddy
*Date   :25 Nov 2024
*Time   :3:19:36 pm
*
*/

public interface DealerRepository extends JpaRepository<Dealer, Long> {
	
	//By default JPARepository has pre-defined methods for CRUD Operations
	//To fetch a single record based on priamry key field
	
	//Custom Methods To fetch a record/object based on nonId field - email

	public Optional<Dealer> findByEmail(String email);
	
	//To fetch a record/object based on lname - nonId field
	public List<Dealer> findByLname(String lname);
	
	

	/*
	 * @Query Annotation is used for defining custom queries in Spring Data JPA. 
	 * When you are unable to use the query methods to execute database operations 
	 * then you can use @Query to write a more flexible query to fetch data. 
	 *  
		- @Query Annotation supports both JPQL and native SQL queries.
		- It also supports SpEL expressions.
		- @Param in method arguments to bind query parameter.
		- To define Multiple Joins
	 */
	//Custom Queries - JPQL - Object Oriented Query Language
	@Query("SELECT new com.coforge.training.producthive.model.DealerAndAddressProjection"
			+ "(d.id,d.fname,d.lname,d.phoneNo,"
			+ "d.email,a.street,a.city,a.pincode)" 
			+ " FROM Dealer d JOIN d.address a")
	List<DealerAndAddressProjection> findSelectedFieldsFromDealerAndAddress(); 


	
	

}
