package ru.job4j.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Timestamp;

public class HibernateRun {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory factory = configuration.configure().buildSessionFactory();

        create(factory);
        update(factory);
        delete(factory);

        factory.close();
    }

    static void create(SessionFactory factory) {
        User user = new User();
        user.setName("test");
        user.setExpired(new Timestamp(System.currentTimeMillis()));
        User user2 = new User();
        user2.setName("test2");
        user2.setExpired(new Timestamp(System.currentTimeMillis()));

        Session session = factory.openSession();
        session.beginTransaction();
        session.save(user);
        session.save(user2);
        System.out.println(session.createQuery("from User").list());
        session.getTransaction().commit();
        session.close();
    }

    static void update(SessionFactory factory) {
        User updated = new User();
        updated.setId(1);
        updated.setName("test upd");

        Session session = factory.openSession();
        session.beginTransaction();
        session.update(updated);
        System.out.println(session.createQuery("from User").list());
        session.getTransaction().commit();
        session.close();
    }

    static void delete(SessionFactory factory) {
        User deleted = new User();
        deleted.setId(1);

        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(deleted);
        System.out.println(session.createQuery("from User").list());
        session.getTransaction().commit();
        session.close();
    }

}
