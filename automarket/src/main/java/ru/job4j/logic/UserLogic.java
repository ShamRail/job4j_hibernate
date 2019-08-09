package ru.job4j.logic;

import ru.job4j.models.annotatedmodels.User;
import ru.job4j.persistance.DAO;
import ru.job4j.persistance.UserDAO;
import java.util.List;

public class UserLogic implements Logic<User> {

    private static final DAO<User> DAO = UserDAO.getInstance();

    private static final UserLogic USER_LOGIC = new UserLogic();

    private UserLogic() {

    }

    public static UserLogic getInstance() {
        return USER_LOGIC;
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
