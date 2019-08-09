package ru.job4j.persistance;

import ru.job4j.models.annotatedmodels.Advertisement;
import java.util.List;

public class AdvertisementDAO implements DAO<Advertisement> {

    private static final AdvertisementDAO ADVERTISEMENT_DAO = new AdvertisementDAO();

    private final DAO<Advertisement> DAO = new TemplateDAO<Advertisement>(Advertisement.class);

    private AdvertisementDAO() {

    }

    public static AdvertisementDAO getInstance() {
        return ADVERTISEMENT_DAO;
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
