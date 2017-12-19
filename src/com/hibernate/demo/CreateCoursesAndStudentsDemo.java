package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

 public class CreateCoursesAndStudentsDemo {

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
			
			Course tempCourse = new Course("Spring Core");
			
			System.out.println("Saving the tempCourse....");
			session.save(tempCourse);
			
			Student theStudent1 = new Student("Justin" , "Bieber", "justin@gmail.com");
			Student theStudent2 = new Student("Ed" , "Shereen", "ed@gmail.com");
			Student theStudent3 = new Student("Ellie" , "Goulding", "ellie@gmail.com");
			
			tempCourse.addStudent(theStudent1);
			tempCourse.addStudent(theStudent2);
			tempCourse.addStudent(theStudent3);
			
			session.save(theStudent1);
			session.save(theStudent2);
			session.save(theStudent3);
			
			session.getTransaction().commit();
		}
		finally{
			session.close();
			
			factory.close();
		}
	}

}
