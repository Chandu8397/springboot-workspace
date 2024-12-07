package com.coforge.training.producthive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coforge.training.producthive.model.Product;
import java.util.List;


/*
*Author :Mekapothula.Reddy
*Date   :23 Nov 2024
*Time   :2:56:58 pm
*
*/

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	/* Long is data type of @Id field of Product Class
	 * 
	 * This interface has save(),findAll(),findById(),deleteById(),count()
       etc.. inbuilt methods of jpa repository for various database operations.
       
       This interface will be implemented by class automatically
	 */
	
	
	//Custom JPL Query
	/*
	 * @Query specifies that you're providing a custom JPQL query.
	 * We use the REPLACE function to remove spaces both from the p.name 
	 * field and from the provided :name, making them both single continuous strings with no spaces.
	 * JPQL query that selects products where the lowercase name 
	 * contains the lowercase input name with wildcards.
	 * % Any no.of Characters & _ - Single character
	 * @Param("name") is used to bind the name parameter from the 
	 * method signature to the :name placeholder in the query
	 */
	//Custom Method to search product based on name - findByName(String name) ;
	//Custom Query to search product based on name -  by ignoring spaces & converting to lowercase
	//Select * from product where name LIKE 'HARD%'
	@Query("SELECT p FROM Product p WHERE LOWER(REPLACE(p.name,' ','')) LIKE " + 
			"LOWER(CONCAT(REPLACE(:name,' ',''),'%'))")			//cellPhone%
	List<Product> findProductByNameContainingIgnoreCase(@Param ("name") String name);
}
