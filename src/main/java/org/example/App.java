package org.example;

import org.example.model.Principal;
import org.example.model.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Principal.class).addAnnotatedClass(School.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            changePrincipalAtExistSchool(session);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    private static void showPrincipalAndSchool(Session session) {
        Principal principal = session.get(Principal.class, 3);
        System.out.println(principal);
        System.out.println(principal.getSchool());
    }

    private static void showSchoolAndPrincipal(Session session) {
        School school = session.get(School.class, 2);
        System.out.println(school);
        System.out.println(school.getOwner());
    }

    private static void createNewPrincipalAndNewSchool(Session session) {
        Principal principal = new Principal("Jane", 48);
        principal.setSchool(new School(9, principal));
        session.save(principal);
        System.out.println(principal.getSchool());
    }

    private static void changePrincipalAtExistSchool(Session session) {
        School school = session.get(School.class, 2);

        System.out.println(school.getOwner());
        school.setOwner(new Principal("Jacob", 38));
        school.getOwner().setSchool(school);

        session.save(school.getOwner());
        System.out.println(school.getOwner());
    }
}
