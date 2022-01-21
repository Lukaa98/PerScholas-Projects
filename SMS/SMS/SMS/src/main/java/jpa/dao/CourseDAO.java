package main.java.jpa.dao;

import java.util.List;

import main.java.jpa.entitymodels.Course;

public interface CourseDAO {

	// get all the courses
	List<Course> getAllCourses();
}
