package ru.job4j.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.function.BiFunction;

public class TemplateMethod {

    private final SessionFactory factory;

    public TemplateMethod(SessionFactory factory) {
        this.factory = factory;
    }

    public  <T, R> R execute(BiFunction<Session, T, R> function, T arg) {
        Session session = factory.openSession();
        session.beginTransaction();
        R data = function.apply(session, arg);
        session.getTransaction().commit();
        session.close();
        return data;
    }

}
