import static org.junit.Assert.*;
import main.java.jpa.entitymodels.Student;
import main.java.jpa.service.StudentService;

import org.junit.Before;
import org.junit.Test;


public class JuinitTest {

	String Email;
	String password;
	Student sut1;
	StudentService stdService ;
	boolean existee = true;

	@Before
	public void setUp() throws Exception {

		 Email="hluckham0@google.ru";
		 password = "X1uZcoIh0dj";
		 sut1= new Student("hluckham0@google.ru", "Hazel Luckham", "X1uZcoIh0dj");
         stdService = new StudentService();


	}

	@Test
	public void testgetStudentByEmail() {

		Student std2 = stdService.getStudentByEmail(Email);
		assertEquals(sut1.getsName(), std2.getsName());}

	@Test
	public void validateStduent() {

		Boolean exi = stdService.validateStduent(Email,password);
		assertEquals(exi, existee);


}

}