package com.code.hibernate.demo;

import com.code.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForPedroDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get the student Pedro from database
            int theId = 3;
            Student student = session.get(Student.class, theId);
            System.out.println("student: " + student);

            // create a courses
            Course course1 = new Course("Build car for 60 minutes");
            Course course2 = new Course("Pick up beauty girls");
            Course course3 = new Course("Play in piano like a Shopen");

            // add student Pedro to courses
            student.add(course1);
            student.add(course2);
            student.add(course3);

            // save the courses
            System.out.println("Saving the courses");
            session.save(course1);
            session.save(course2);
            session.save(course3);
            System.out.println("Saved courses " + student.getCourseList());

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Everything are done.");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
    }
}
