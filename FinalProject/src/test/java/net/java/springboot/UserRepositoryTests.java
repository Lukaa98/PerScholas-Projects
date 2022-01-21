package net.java.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import net.java.springboot.Luka.Shop.model.User;
import net.java.springboot.Luka.Shop.repository.UserRepository;
 
@DataJpaTest //this class will be running as JPA test
@AutoConfigureTestDatabase(replace = Replace.NONE) //to use real database not in memory database
@Rollback(false)
public class UserRepositoryTests {
 
	
	@Autowired
	private UserRepository userRepository;
	     
    @Autowired
    private TestEntityManager testentityManager;
     
    // test methods go below
    //This is a basic test class for testing Spring Data JPA repositories.
    //It is configured to work with the actual database 
    //(@AutoConfigureTestDatabase(replace = Replace.NONE)) 
    //and commit the changes (@Rollback(false)). TestEntityManager is a 
    //wrapper of JPA’s EntityManager so we can use it in test class like a standard EntityManager.
    //And write the first test method that persists a User object into the database 
    
    
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("Nikabaluuka@gmail.com");
        user.setPassword("Luka2022");
        user.setFirstName("Luka");
        user.setLastName("Nikabadze");
         
        User savedUser = userRepository.save(user);
		User existUser = testentityManager.find(User.class, savedUser.getId());
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
        
         
    }
    
    
    @Test
    public void TestFindUserByEmail()
    {
    	String email = "niqabadzelddduka@gmail.com";
    	
    	User user = userRepository.findByEmail(email);
    
    	assertThat(user).isNotNull();
    }
    
    
    
    
    
}