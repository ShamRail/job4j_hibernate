package todolist.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import todolist.models.Item;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiFunction;

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
        wrap(Session::save, item);
    }

    public List<Item> findAll() {
        return wrap((session, arg) -> {
            List<Item> items = session.createQuery("from Item").list();
            return (List<Item>) new CopyOnWriteArrayList<Item>(items);
        }, null);
    }

    public Item findById(int id) {
        return wrap((session, arg) -> (Item) session.createQuery("from items").list().get(0), id);
    }

    public void update(Item item) {
        wrap((session, arg) -> {
            Item updated = session.get(Item.class, item.getId());
            updated.setDone(arg.getDone());
            session.update(arg);
            return null;
        }, item);
    }

    public void delete(Item item) {
        wrap((session, arg) -> {
            session.delete(arg);
            return null;
        }, item);
    }

    private  <T, R> R wrap(BiFunction<Session, T, R> function, T arg) {
        Session session = factory.openSession();
        session.beginTransaction();
        R data = function.apply(session, arg);
        session.getTransaction().commit();
        session.close();
        return data;
    }

}
