package main.java.jpa.entitymodels;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="Course",schema="school_management_system")
public class Course implements Serializable{

	// Unique course Identifier
	@Id
	@Column(name="id")
	private int id;
	//Provides the name of the course
	@Column(name="name")
	private String cName;
   //Provides the name of the instructor
	@Column(name="instructor")
	private String cInstructorName;


	public Course() {

		this.id = 2;
		this.cName = "Math";
		this.cInstructorName = "Course Math";

	}
	public Course(int id, String cName, String cInstructorName) {

		this.id = id;
		this.cName = cName;
		this.cInstructorName = cInstructorName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcInstructorName() {
		return cInstructorName;
	}
	public void setcInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}

	@Override
	public String toString() {
		return  id + "         " + cName + "                   " + getcInstructorName() + "";
	}

}
