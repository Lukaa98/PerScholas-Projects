package main.java.jpa.entitymodels;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Student",schema="school_management_system")
public class Student implements Serializable{

	//Student’s current school email, unique student identifier
	@Id
	@Column(name="email")
	private String sEmail ;


	//The full name of the student
	@Column(name="name")
	private String sName;

	// Student’s password in order to log in
	@Column(name="password")
	private String sPass ;

	//All the courses that a student’s registered for
	@JoinTable(
	        name = "Student_Course",
	        joinColumns = @JoinColumn(
	                name = "ID_Stud",
	                referencedColumnName = "email"
	        ),
	        inverseJoinColumns = @JoinColumn(
	                name = "ID_Course",
	                referencedColumnName = "id"
	        )
	)
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Course> sCourses ;

	public Student() {

		this.sEmail = "mail@test";
		this.sName = "Auurelie";
		this.sPass = "1234";

	}
	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPass() {
		return sPass;
	}

	public void setsPass(String sPass) {
		this.sPass = sPass;
	}

	public List<Course> getsCourses() {
		return sCourses;
	}

	public void setsCourses(List<Course> sCourses) {
		this.sCourses = sCourses;
	}

	public Student(String sEmail, String sName, String sPass) {
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
	}
	@Override
	public String toString() {
		return "Student [sEmail=" + sEmail + ", sName=" + sName + ", sPass="
				+ sPass + ", sCourses=" + sCourses + ", getsEmail()="
				+ getsEmail() + ", getsName()=" + getsName() + ", getsPass()="
				+ getsPass() +"]";
	}



}
