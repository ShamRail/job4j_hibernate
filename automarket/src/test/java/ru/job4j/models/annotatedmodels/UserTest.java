package ru.job4j.models.annotatedmodels;

import org.hamcrest.core.Is;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.utils.HibernateUtil;
import ru.job4j.utils.TemplateMethod;
import java.util.HashSet;
import java.util.Set;

public class UserTest {

    private static final SessionFactory FACTORY = HibernateUtil.getInstance();

    private TemplateMethod method = new TemplateMethod(FACTORY);

    @Test
    public void whenAddThenSave() {
        User user = new User(1);
        Role role = new Role("admin");
        User rstl = method.execute((s, u) -> {
            s.save(u);
            s.save(role);
            u.setRole(role);
            User res = getFirst(s);
            s.delete(u);
            return res;
        }, user);
        Assert.assertThat(rstl, Is.is(user));
    }

    @Test
    public void whenUpdateThenUpdated() {
        User user = new User(1);
        User rstl = method.execute((s, u) -> {
            s.save(u);
            s.evict(u);
            u.setName("test");
            s.update(user);
            User res = getFirst(s);
            s.delete(u);
            return res;
        }, user);
        Assert.assertThat(rstl.getName(), Is.is("test"));
    }

    @Test
    public void whenSaveWithAdsThenMustProperlySaved() {
        User user = new User(1);
        Advertisement advertisement = new Advertisement();
        advertisement.setTitle("first");
        User rstl = method.execute((s, u) -> {
            s.save(advertisement);
            s.save(u);
            Set<Advertisement> ads = new HashSet<>();
            ads.add(advertisement);
            u.setAds(ads);
            User res = getFirst(s);
            s.delete(u);
            return res;
        }, user);
        Assert.assertThat(rstl.getAds().iterator().next().getTitle(), Is.is("first"));
    }

    private User getFirst(Session session) {
        return (User) session.createQuery("from User").list().get(0);
    }

}