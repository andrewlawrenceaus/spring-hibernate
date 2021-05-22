package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMaryDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        // use the session object to save the Java object
        try {

            // start a transaction
            session.beginTransaction();

            // get the student mary from the database
            int studentId = 2;
            Student tempStudent = session.get(Student.class, studentId);

            System.out.println("\nLoaded student: " + tempStudent);
            System.out.println("Courses: " + tempStudent.getCourses());

            // create more courses
            Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
            Course tempCourse2 = new Course("Atari 2600 - Game Development");

            // add students to courses
            tempCourse1.addStudent(tempStudent);
            tempCourse2.addStudent(tempStudent);

            // save the courses
            session.save(tempCourse1);
            session.save(tempCourse2);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }

    }
}
