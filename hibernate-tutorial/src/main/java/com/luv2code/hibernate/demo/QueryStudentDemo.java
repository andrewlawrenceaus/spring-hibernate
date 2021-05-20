package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        // use the session object to save the Java object
        try {

            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            // display the students
            displayStudents(theStudents);

            // query students: lastName='Doe'
            theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

            // display the students who have last name of doe
            System.out.println("\n\nStudents who have last name of doe");
            displayStudents(theStudents);

            // query students: lastName='Doe' OR firstName='Daffy'
            theStudents = session.createQuery("from Student s where " +
                    "s.lastName='Doe' OR s.firstName='Daffy'").getResultList();

            // display the students who have last name of Doe or first name of Daffy
            System.out.println("\n\nStudents who have last name of Doe or first name of Daffy");
            displayStudents(theStudents);

            // query students where email LIKE '%gmail.com'
            theStudents = session.createQuery("from Student s where " +
                    "s.email LIKE '%gmail.com'").getResultList();
            System.out.println("\n\nStudents where email LIKE '%gmail.com'");
            displayStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();

        } finally {
            factory.close();
        }

    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }
}
