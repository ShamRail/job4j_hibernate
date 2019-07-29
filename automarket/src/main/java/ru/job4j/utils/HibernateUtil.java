package ru.job4j.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final Configuration CONFIGURATION = new Configuration();

    private static final  SessionFactory FACTORY = CONFIGURATION.configure().buildSessionFactory();

    private HibernateUtil() {

    }

    public static SessionFactory getInstance() {
        return FACTORY;
    }

}
