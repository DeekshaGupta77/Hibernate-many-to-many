package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

 public class AddCoursesToStudentDemo {

public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).
				addAnnotatedClass(InstructorDetail.class).
				addAnnotatedClass(Course.class).
				addAnnotatedClass(Review.class).
				addAnnotatedClass(Student.class).
				buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try{
			
			session.beginTransaction();
			
			int studentId = 2;
			Student theStudent = session.get(Student.class, studentId);
			
			System.out.println("Student with id=2 is...." +theStudent);
			System.out.println("Courses assigned are: " +theStudent.getCourses());
			
			Course course1 = new Course("Hibernate");
			Course course2 = new Course("SQL Database");
			
			course1.addStudent(theStudent);
			course2.addStudent(theStudent);
			
			session.save(course1);
			session.save(course2);
			
			session.getTransaction().commit();
		}
		finally{
			session.close();
			
			factory.close();
		}
	}

}
