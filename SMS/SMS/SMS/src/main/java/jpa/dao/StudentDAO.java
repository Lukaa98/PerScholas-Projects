package main.java.jpa.dao;

import java.util.List;

import main.java.jpa.entitymodels.Course;
import main.java.jpa.entitymodels.Student;

public interface StudentDAO {
	

	   //get all students from database
		List<Student> getAllStudents();

		//find a student with eail and return the object
		Student getStudentByEmail(String sEmail);

		//validate student credentials
		boolean validateStduent(String sEmail, String sPassword);

		//register student to course
		void registerStudentToCourse(String sEmail, int cID);

		//get all the courses the student is registered for;
		List<Course> getStudentCourses(String sEmail);

}
