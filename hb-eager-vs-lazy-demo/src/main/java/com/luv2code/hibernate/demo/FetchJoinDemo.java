package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class FetchJoinDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        // use the session object to save the Java object
        try {

            // start a transaction
            session.beginTransaction();

            // get the instructor from db
            int theId = 1;

            Query<Instructor> query = session.createQuery("select i from Instructor i "
                            + "JOIN FETCH i.courses "
                            + "where i.id=:theInstructorId",
                    Instructor.class);

            query.setParameter("theInstructorId", theId);
            Instructor tempInstructor = query.getSingleResult();



            System.out.println("Instructor: " + tempInstructor);


            // commit transaction
            session.getTransaction().commit();
            session.close();
            System.out.println("Done!");
            System.out.println("Courses: " + tempInstructor.getCourses());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }


    }
}
