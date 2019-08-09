package ru.job4j.persistance;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.models.annotatedmodels.Advertisement;
import ru.job4j.models.annotatedmodels.Role;
import ru.job4j.models.annotatedmodels.User;
import java.util.*;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class UserDAOTest {

    private final static DAO<User> DAO = UserDAO.getInstance();

    @Test
    public void testAdd() {
        User user = new User();
        user.setName("test user");
        int id = DAO.create(user);
        assertThat(DAO.getById(id).getName(), is("test user"));
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setName("test user");
        int id = DAO.create(user);
        User newUser = new User();
        newUser.setName("test updated");
        DAO.update(id, newUser);
        assertThat(DAO.getById(id).getName(), is("test updated"));
    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setName("test");
        DAO.create(user);
        DAO.delete(user);
        Collection<User> users = DAO.read();
        assertFalse(users.contains(user));
    }

    @Test
    public void testUserRole() {
        User user = new User();
        Role role = new Role("test");
        user.setRole(role);
        int id = DAO.create(user);
        assertThat(DAO.getById(id).getRole().getName(), is("test"));
    }

    @Test
    public void testUserAds() {
        User user = new User();
        Advertisement ad = new Advertisement();
        ad.setTitle("test");
        Set<Advertisement> ads = new LinkedHashSet<>();
        ads.add(ad);
        user.setAds(ads);
        ad.setUser(user);
        int id = DAO.create(user);
        User result = DAO.getById(id);
        assertThat(result.getAds().iterator().next().getTitle(), is(ad.getTitle()));
    }

    @Test
    public void testGetByQuery() {
        User user = new User();
        int before = user.getId();
        int id = DAO.create(user);
        int after = user.getId();
        List<User> users = DAO.getByQuery("from User");
        Assert.assertTrue(users.contains(user));
    }

    @Test
    public void testSaveAndThenUpdateAds() {
        User user = new User();
        int id = DAO.create(user);
        Advertisement ad = new Advertisement();
        ad.setTitle("test");
        ad.setUser(user);
        user.setAds(new LinkedHashSet<>(Arrays.asList(ad)));
        DAO.update(id, user);
        assertThat(DAO.getById(id).getAds().iterator().next().getTitle(), is("test"));
    }

    @Test
    public void testGetByNameAndPassword() {
        User user = new User();
        User user1 = new User();
        user.setName("user1");
        user.setPassword("password1");
        user1.setName("user2");
        user1.setName("password2");
        DAO.create(user);
        DAO.create(user1);
        List<User> users = DAO.getByQuery("from User where name = 'user1' and password = 'password1'");
        assertThat(users.get(0), is(user));
    }

    @Test
    public void testWhenAddAdToUser() {
        User user = new User();
        int id = DAO.create(user);
        Advertisement ad = new Advertisement();
        ad.setTitle("test");
        ad.setUser(user);
        user.setAds(new LinkedHashSet<>(Arrays.asList(ad)));
        DAO.update(id, user);
        assertThat(DAO.getById(id).getAds().iterator().next().getTitle(), is("test"));
    }

}