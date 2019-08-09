package ru.job4j.persistance;

import ru.job4j.models.annotatedmodels.User;
import java.util.List;

public class UserDAO implements DAO<User> {

    private static final UserDAO USER_DAO = new UserDAO();

    private final DAO<User> DAO = new TemplateDAO<User>(User.class);

    private UserDAO() {

    }

    public static UserDAO getInstance() {
        return USER_DAO;
    }

    @Override
    public int create(User obj) {
        return DAO.create(obj);
    }

    @Override
    public List<User> read() {
        return DAO.read();
    }

    @Override
    public void update(int id, User newObj) {
        DAO.update(id, newObj);
    }

    @Override
    public void delete(User obj) {
        DAO.delete(obj);
    }

    @Override
    public User getById(int id) {
        return DAO.getById(id);
    }

    @Override
    public List<User> getByQuery(String query) {
        return DAO.getByQuery(query);
    }
}
