import static java.lang.System.out;

import java.util.List;
import java.util.Scanner;

import main.java.jpa.entitymodels.Course;
import main.java.jpa.entitymodels.Student;
import main.java.jpa.service.CourseService;
import main.java.jpa.service.StudentService;

public class SMSRunner {

	private Scanner sin;
	private StringBuilder sb;

	private CourseService courseService;
	private StudentService studentService;
	private Student currentStudent;

	public SMSRunner() {
		sin = new Scanner(System.in);
		sb = new StringBuilder();
		courseService = new CourseService();
		studentService = new StudentService();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SMSRunner sms = new SMSRunner();
		sms.run();
	}

	private void run() {
		// Login or quit
		switch (menu1()) {
		case 1:
			if (studentLogin()) {
				registerMenu();
			}
			break;
		case 2:
			out.println("Goodbye!");
			break;

		default:

		}
	}

	private int menu1() {
		sb.append("\n1.Student Login\n2. Quit Application\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		return sin.nextInt();
	}

	private boolean studentLogin() {
		boolean retValue = false;
		out.print("Enter your email address: ");
		String email = sin.next();
		out.print("Enter your password: ");
		String password = sin.next();

		boolean student =  studentService.validateStduent(email, password);
		if (student == true) {
			currentStudent = studentService.getStudentByEmail(email);
		}

		if (currentStudent != null) {
			List<Course> courses = studentService.getStudentCourses(email);
			out.println("MyClasses");
			if(courses!=null) {
		   out.printf("%5s%15S%15s\n", "#        ", "COURSE NAME                ", "INSTRUCTOR NAME");
			for (Course course : courses) {
				out.println(course);
			}}
			retValue = true;
		} else {
			out.println("User Validation failed. GoodBye!");
		}
		return retValue;
	}

	private void registerMenu() {
		sb.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		switch (sin.nextInt()) {
		case 1:
			List<Course> allCourses = courseService.getAllCourses();
			List<Course> studentCourses = studentService.getStudentCourses(currentStudent.getsEmail());
			allCourses.removeAll(studentCourses);
			   out.printf("%5s%15S%15s\n", "#        ", "COURSE NAME                ", "INSTRUCTOR NAME");
			for (Course course : allCourses) {
				out.println(course);
			}
			out.println();
			out.print("Which Course ? ");
			int number = sin.nextInt();
			String newCourse = null ;
			for (int i = 0; i < allCourses.size(); i++) {

				if(allCourses.get(i).getId()==number) {newCourse="Yes";}

			}

			if (newCourse != null) {
				studentService.registerStudentToCourse(currentStudent.getsEmail(), number);
				Student temp = studentService.getStudentByEmail(currentStudent.getsEmail());

				List<Course> sCourses = studentService.getStudentCourses(temp.getsEmail());

				out.println("MyClasses");
				   out.printf("%5s%15S%15s\n", "#        ", "COURSE NAME                ", "INSTRUCTOR NAME");
				for (Course course : sCourses) {
					out.println(course);
				}

				out.println("You have been signed out.!");
			}
			break;
		case 2:
		default:
			out.println("Goodbye!");
		}
	}
}
