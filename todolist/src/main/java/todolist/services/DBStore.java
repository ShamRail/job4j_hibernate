package todolist.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import todolist.models.Item;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DBStore implements Store{

    private final Configuration configuration;

    private final SessionFactory factory;

    private static final Store INSTANCE = new DBStore();

    private DBStore() {
        configuration = new Configuration();
        factory = configuration.configure().buildSessionFactory();
    }

    public static Store getInstance() {
        return INSTANCE;
    }

    public void create(Item item) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.save(item);

        session.getTransaction().commit();
        session.close();
    }

    public List<Item> findAll() {
        Session session = factory.openSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("from Item").list();
        List<Item> list = new CopyOnWriteArrayList<Item>(items);
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public Item findById(int id) {
        Session session = factory.openSession();
        session.beginTransaction();
        Item item = (Item) session.createQuery("from items").list().get(0);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public void update(Item item) {
        Session session = factory.openSession();
        session.beginTransaction();
        Item updated = session.get(Item.class, item.getId());
        updated.setDone(item.getDone());
        session.update(updated);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Item item) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.delete(item);

        session.getTransaction().commit();
        session.close();
    }
}
