package ru.job4j.logic;

import ru.job4j.models.annotatedmodels.Advertisement;
import ru.job4j.persistance.AdvertisementDAO;
import ru.job4j.persistance.DAO;
import java.util.List;

public class AdvertisementLogic implements Logic<Advertisement> {

    private static final DAO<Advertisement> DAO = AdvertisementDAO.getInstance();

    private static final AdvertisementLogic ADVERTISEMENT_LOGIC = new AdvertisementLogic();

    private AdvertisementLogic() {

    }

    public static AdvertisementLogic getInstance() {
        return ADVERTISEMENT_LOGIC;
    }

    @Override
    public int create(Advertisement obj) {
        return DAO.create(obj);
    }

    @Override
    public List<Advertisement> read() {
        return DAO.read();
    }

    @Override
    public void update(int id, Advertisement newObj) {
        DAO.update(id, newObj);
    }

    @Override
    public void delete(Advertisement obj) {
        DAO.delete(obj);
    }

    @Override
    public Advertisement getById(int id) {
        return DAO.getById(id);
    }

    @Override
    public List<Advertisement> getByQuery(String query) {
        return DAO.getByQuery(query);
    }

}
