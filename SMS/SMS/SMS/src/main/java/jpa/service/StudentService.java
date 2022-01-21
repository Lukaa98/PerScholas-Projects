package main.java.jpa.service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import main.java.jpa.dao.StudentDAO;
import main.java.jpa.entitymodels.Course;
import main.java.jpa.entitymodels.Student;

public class StudentService implements   StudentDAO{


	//  create sessionFactory
	public static final SessionFactory sessionFactory = new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
	public static Session session;


	//get all students from database
	public List<Student> getAllStudents() {

		List<Student> students = new ArrayList<Student>();
		// open session
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		// get All data in table Student
		students = session.createQuery("from Student").list();
		// commit
		session.getTransaction().commit();
		return students;
	}

	//find a student with meail and return the object
	public Student getStudentByEmail(String sEmail) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		// get Student with Email = sEmail
		Student std = (Student) session.load(Student.class, sEmail);
		return std;
	}

	//validate student credentials
	public boolean validateStduent(String sEmail, String sPassword) {

		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		// get Student with Email = sEmail
		Student std = (Student) session.load(Student.class, sEmail);

		if(std !=null) {
			// if password is correct return true else return false
			if(std.getsPass().equals(sPassword)) { session.getTransaction().commit();return true; }
		}

		session.getTransaction().commit();
		return false;
	}

	//register student to course
	public void registerStudentToCourse(String sEmail, int cID) {

		int Resl = -1;
		session = sessionFactory.getCurrentSession();
		// get Student with Email = sEmail
		Student std = (Student) session.load(Student.class, sEmail);
		// get Course with ID = cID
	 	Course coursee = (Course) session.load(Course.class, cID);
		List<Course> listeCourse = new ArrayList<Course>();
		// get All Course of student std
		List<Course> listeCourses = std.getsCourses();

		// if the course is already assigned to the Student, do nothing, otherwise assign the course to the student
		for (int i = 0; i < listeCourses.size(); i++) {

			if(listeCourses.get(i).getId()==cID) {

				Resl=0;

			}
		}

		if(Resl==0) {
			System.out.println("Not");
			session.getTransaction().commit();
		}
		else {
			std.getsCourses().add(coursee);
			session.save(std);

			session.getTransaction().commit();
		}

	}

	//get all the courses the student is registered for;
	public List<Course> getStudentCourses(String sEmail) {

		session = sessionFactory.getCurrentSession();
		Student std = (Student) session.load(Student.class, sEmail);
		List<Course> listeCourse = new ArrayList<Course>();
		if(std !=null) {
			listeCourse = std.getsCourses();
			return listeCourse;

		}
		return null;
	}






}
