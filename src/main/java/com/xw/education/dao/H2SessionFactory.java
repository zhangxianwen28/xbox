package com.xw.education.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class H2SessionFactory {

    public static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("InitialSessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}
