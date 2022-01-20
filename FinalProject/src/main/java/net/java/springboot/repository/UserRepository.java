package net.java.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.java.springboot.model.User;

public interface UserRepository  extends JpaRepository<User, Long>
					// User is entity class. and Long an ID
{
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);

}
