package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;

 public class CreateCoursesAndReviewsDemo {

public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).
				addAnnotatedClass(InstructorDetail.class).
				addAnnotatedClass(Course.class).
				addAnnotatedClass(Review.class).
				buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try{
			
			session.beginTransaction();
			
			Course tempCourse = new Course("Spring Core");
			
			tempCourse.addReview(new Review("Good Course..."));
			tempCourse.addReview(new Review("You have a Great knowledge..."));
			tempCourse.addReview(new Review("A bad one"));
			
			session.save(tempCourse);
			
			session.getTransaction().commit();
		}
		finally{
			session.close();
			
			factory.close();
		}
	}

}
