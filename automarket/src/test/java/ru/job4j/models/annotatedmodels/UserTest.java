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
import java.util.function.BiFunction;

public class UserTest {

    private static final SessionFactory FACTORY = HibernateUtil.getInstance();

    private TemplateMethod method = new TemplateMethod(FACTORY);

    @Test
    public void whenAddThenSave() {
        User user = new User(1);
        user.setName("t1");
        Role role = new Role("admin");
        method.execute((s, u) -> {
            s.save(u);
            s.save(role);
            u.setRole(role);
            return null;
        }, user);
        User rstl = getByName("t1");
        Assert.assertThat(rstl.getName(), Is.is(user.getName()));
    }

    @Test
    public void whenUpdateThenUpdated() {
        User user = new User(1);
        method.execute((s, u) -> {
            s.save(u);
            s.evict(u);
            u.setName("test");
            s.update(user);
            return null;
        }, user);
        User rstl = getByName("test");
        Assert.assertThat(rstl.getName(), Is.is("test"));
    }

    @Test
    public void whenSaveWithAdsThenMustProperlySaved() {
        User user = new User(1);
        user.setName("t2");
        method.execute((s, u) -> {
            Advertisement a = new Advertisement();
            a.setTitle("first");
            s.save(a);
            s.save(u);
            Set<Advertisement> ads = new HashSet<>();
            ads.add(a);
            u.setAds(ads);
            a.setUser(u);

            return null;
        }, user);
        User rstl = getByName("t2");
        Assert.assertThat(rstl.getAds().iterator().next().getTitle(), Is.is("first"));
    }

    private User getFirst(Session session) {
        return (User) session.createQuery("from User").list().get(0);
    }

    private User getByName(String name) {
        return method.execute((session, o)
                -> (User) session.createQuery(String.format("from User as u where u.name='%s'", name))
                .list().iterator().next(), null
        );
    }

}