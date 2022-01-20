package net.java.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.java.springboot.model.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long>// product is entity class. and Long an ID
{
	
	List<Product> findAllByCategory_id(int id);

}
