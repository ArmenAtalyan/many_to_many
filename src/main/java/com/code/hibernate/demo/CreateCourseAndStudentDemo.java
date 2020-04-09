package com.code.hibernate.demo;

import com.code.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentDemo {

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

            // create a course
            Course course = new Course("Driving car first time");

            // get the course object

            // save the course
            System.out.println("Saving the course.");
            session.save(course);
            System.out.println("Saved course: " + course);

            // create the students
            Student student1 = new Student("Vardan", "Karapetyan", "VK@gmail.com");
            Student student2 = new Student("Sati", "Grigoryan", "satgrig@gmail.com");
            Student student3 = new Student("Don", "Pedro", "donped@gmail.com");

            // add students to the course
            course.addStudent(student1);
            course.addStudent(student2);
            course.addStudent(student3);

            // save the students
            System.out.println("Saving students");
            session.save(student1);
            session.save(student2);
            session.save(student3);
            System.out.println("Saved students: " + course.getStudentList());

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
