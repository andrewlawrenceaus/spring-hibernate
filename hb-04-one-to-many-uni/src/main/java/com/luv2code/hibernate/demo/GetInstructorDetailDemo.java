package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("src/main/resources/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        // use the session object to save the Java object
        try {
            // start a transaction
            session.beginTransaction();

            // get the instructor detail object
            int theId = 3;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

            // print the instructor detail
            System.out.println("tempInstructorDetail: " + tempInstructorDetail);

            // print the associated instructor
            System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            // now let's delete the instructor detail
            System.out.println("Deleting tempInstructorDetail: "
                    + tempInstructorDetail);
            session.delete(tempInstructorDetail);

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
