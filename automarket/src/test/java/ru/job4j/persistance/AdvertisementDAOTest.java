package ru.job4j.persistance;

import org.junit.Test;
import ru.job4j.models.annotatedmodels.Advertisement;
import ru.job4j.models.annotatedmodels.Car;
import ru.job4j.models.annotatedmodels.User;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AdvertisementDAOTest {

    private static final DAO<Advertisement> DAO = AdvertisementDAO.getInstance();

    private static final DAO<User> USER_DAO = UserDAO.getInstance();

    @Test
    public void testCreate() {
        Advertisement ad = new Advertisement();
        int id = DAO.create(ad);
        assertThat(DAO.getById(id), is(ad));
    }

    @Test
    public void testUpdate() {
        Advertisement ad = new Advertisement();
        int id = DAO.create(ad);
        ad.setTitle("test");
        DAO.update(id, ad);
        assertThat(DAO.getById(id).getTitle(), is("test"));
    }

    @Test
    public void testDelete() {
        Advertisement ad = new Advertisement();
        int id = DAO.create(ad);
        DAO.delete(ad);
        assertNull(DAO.getById(id));
    }

    @Test
    public void testGetByQuery() {
        Advertisement ad = new Advertisement();
        int id = DAO.create(ad);
        assertTrue(DAO.getByQuery("from Advertisement").contains(ad));
    }

    @Test
    public void testGetWithUser() {
        Advertisement ad = new Advertisement();
        ad.setTitle("test ad");
        User user = new User();
        user.setName("test");
        ad.setUser(user);
        user.setAds(new LinkedHashSet<>(Arrays.asList(ad)));
        USER_DAO.create(user);
        int id = DAO.create(ad);
        Advertisement result = DAO.getById(id);
        assertThat(result.getUser().getName(), is("test"));
    }

    @Test
    public void testGetWithCar() {
        Advertisement ad = new Advertisement();
        ad.setTitle("test ad");
        Car car = new Car();
        car.setMark("test");
        ad.setCar(car);
        int id = DAO.create(ad);
        Advertisement result = DAO.getById(id);
        assertThat(result.getCar().getMark(), is("test"));
    }

    @Test
    public void testQueryByStatus() {
        Advertisement ad = new Advertisement();
        ad.setStatus(false);
        ad.setTitle("test ad");
        Car car = new Car();
        car.setMark("test");
        ad.setCar(car);

        Advertisement ad2 = new Advertisement();
        ad2.setTitle("test ad2");
        ad2.setStatus(true);
        Car car2 = new Car();
        car2.setMark("test2");
        ad2.setCar(car2);


        DAO.create(ad);
        DAO.create(ad2);
        List<Advertisement> result = DAO.getByQuery("from Advertisement where status = true");
        assertThat(result.get(0).getCar().getMark(), is("test2"));
    }

    @Test
    public void testQueryByPhoto() {

        Advertisement ad = new Advertisement();
        ad.setPhotoPath("some/path");
        ad.setStatus(false);
        ad.setTitle("test ad");
        Car car = new Car();
        car.setMark("test");
        ad.setCar(car);

        Advertisement ad2 = new Advertisement();
        ad2.setTitle("test ad2");
        ad2.setStatus(true);
        Car car2 = new Car();
        car2.setMark("test2");
        ad2.setCar(car2);


        DAO.create(ad);
        DAO.create(ad2);
        List<Advertisement> result = DAO.getByQuery("from Advertisement where photoPath <> null");
        assertThat(result.get(0).getCar().getMark(), is("test"));
    }

    @Test
    public void testQueryByMark() {

        Advertisement ad = new Advertisement();
        ad.setPhotoPath("some/path");
        ad.setStatus(false);
        ad.setTitle("test ad");
        Car car = new Car();
        car.setMark("test");
        ad.setCar(car);

        Advertisement ad2 = new Advertisement();
        ad2.setTitle("test ad2");
        ad2.setStatus(true);
        Car car2 = new Car();
        car2.setMark("test2");
        ad2.setCar(car2);


        DAO.create(ad);
        DAO.create(ad2);
        List<Advertisement> result = DAO.getByQuery("from Advertisement as ad where ad.car.mark='test2'");

        assertThat(result.get(0).getTitle(), is("test ad2"));
    }

    @Test
    public void testQuerySort() {

        Advertisement ad = new Advertisement();
        ad.setDate(new Timestamp(System.currentTimeMillis()));
        ad.setTitle("ad");

        Advertisement ad2 = new Advertisement();
        ad2.setTitle("ad2");
        ad2.setDate(new Timestamp(System.currentTimeMillis()));

        DAO.create(ad);
        DAO.create(ad2);
        List<Advertisement> result = DAO.getByQuery("from Advertisement as ad order by ad.id desc");

        assertThat(result.get(0).getTitle(), is("ad2"));
    }

}