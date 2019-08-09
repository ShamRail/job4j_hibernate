package ru.job4j.persistance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.models.Unique;
import ru.job4j.utils.HibernateUtil;
import java.util.LinkedList;
import java.util.List;

public class TemplateDAO<T extends Unique> implements DAO<T> {

    private static final SessionFactory FACTORY = HibernateUtil.getInstance();

    private static final Logger LOG = LogManager.getLogger(TemplateDAO.class.getName());

    private final Class<T> type;

    public TemplateDAO(Class type) {
        this.type = type;
    }

    @Override
    public int create(T obj) {
        LOG.debug("###############################################");
        LOG.debug("CREATE {}", type.getSimpleName());
        int id = -1;
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            LOG.debug("Transaction opened");
            session.save(obj);
            LOG.debug("Object saved");
            id = obj.getId();
            session.getTransaction().commit();
            LOG.debug("Transaction finished");
        } catch (Exception ex) {
            LOG.error("Operation execution failed");
            LOG.error("Exception message: {}", ex.getMessage());
        }
        LOG.debug("############################################### {}", System.lineSeparator());
        return id;
    }

    @Override
    public List<T> read() {
        LOG.debug("###############################################");
        LOG.debug("READ from {}", type.getSimpleName());
        List<T> objs = new LinkedList<>();
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            LOG.debug("Transaction opened");
            objs.addAll(session.createQuery("from " + type.getSimpleName()).list());
            LOG.debug("Objects retrieved");
            session.getTransaction().commit();
            LOG.debug("Transaction finished");
        } catch (Exception ex) {
            LOG.error("Operation execution failed");
            LOG.error("Exception message: {}", ex.getMessage());
        }
        LOG.debug("###############################################");
        return objs;
    }

    @Override
    public void update(int id, T newObj) {
        LOG.debug("###############################################");
        LOG.debug("UPDATE {}", type.getSimpleName());
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            LOG.debug("Transaction opened");
            newObj.setId(id);
            session.update(newObj);
            LOG.debug("Object updated");
            session.getTransaction().commit();
            LOG.debug("Transaction finished");
        } catch (Exception ex) {
            LOG.error("Operation execution failed");
            LOG.error("Exception message: {}", ex.getMessage());
        }
        LOG.debug("###############################################");
    }

    @Override
    public void delete(T obj) {
        LOG.debug("###############################################");
        LOG.debug("DELETE {}", type.getSimpleName());
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            LOG.debug("Transaction opened");
            session.delete(obj);
            LOG.debug("Object deleted");
            session.getTransaction().commit();
            LOG.debug("Transaction finished");
        } catch (Exception ex) {
            LOG.error("Operation execution failed");
            LOG.error("Exception message: {}", ex.getMessage());
        }
        LOG.debug("###############################################");
    }

    @Override
    public T getById(int id) {
        T user = null;
        LOG.debug("###############################################");
        LOG.debug("GET BY ID {}", type.getSimpleName());
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            LOG.debug("Transaction opened");
            user = session.get(type, id);
            LOG.debug("Object retrieved");
            session.getTransaction().commit();
            LOG.debug("Transaction finished");
        } catch (Exception ex) {
            LOG.error("Operation execution failed");
            LOG.error("Exception message: {}", ex.getMessage());
        }
        LOG.debug("###############################################");
        return user;
    }

    @Override
    public List<T> getByQuery(String query) {
        LOG.debug("###############################################");
        LOG.debug("GEY BY QUERY {}", type.getSimpleName());
        LOG.debug("QUERY {}", query);
        List<T> users = new LinkedList<>();
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            LOG.debug("Transaction opened");
            users.addAll(session.createQuery(query).list());
            LOG.debug("Objects retrieved");
            session.getTransaction().commit();
            LOG.debug("Transaction finished");
        } catch (Exception ex) {
            LOG.error("Operation execution failed");
            LOG.error("Exception message: {}", ex.getMessage());
        }
        LOG.debug("###############################################");
        return users;
    }
}
