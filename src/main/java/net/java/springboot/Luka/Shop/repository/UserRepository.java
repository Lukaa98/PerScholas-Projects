package net.java.springboot.Luka.Shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.java.springboot.Luka.Shop.model.User;

public interface UserRepository  extends JpaRepository<User, Long>
					// User is entity class. and Long an ID
{
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);

}
