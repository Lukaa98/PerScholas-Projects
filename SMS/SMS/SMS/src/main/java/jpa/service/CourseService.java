package main.java.jpa.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import main.java.jpa.dao.CourseDAO;
import main.java.jpa.entitymodels.Course;


public class CourseService  implements   CourseDAO{
//
	//  create sessionFactory
	public static  SessionFactory sessionFactory = new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
	Session session = sessionFactory.getCurrentSession();


	@Override
	// get all the courses
	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<Course>();
		// open Transaction
		session.beginTransaction();
		// select all data in table Course
		courses = session.createQuery("from Course").list();
		// commit
		session.getTransaction().commit();
		return courses;
	}

}
