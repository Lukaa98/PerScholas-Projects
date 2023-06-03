package net.java.springboot.Luka.Shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.java.springboot.Luka.Shop.model.Category;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer> // Category is entity class. and Integer an ID
{
	//JPA inlcudes CRUD Repository. so we can use methods provided by JPA in our services class like Add delete update 
	 

}
