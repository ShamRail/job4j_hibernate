package ru.job4j.models.annotatedmodels;

import org.hamcrest.core.Is;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.utils.HibernateUtil;
import ru.job4j.utils.TemplateMethod;

public class RoleTest {

    private static final SessionFactory FACTORY = HibernateUtil.getInstance();

    private TemplateMethod method = new TemplateMethod(FACTORY);

    @Test
    public void whenRetrieveUserByRoleThenReturnUser() {
        User user1 = new User(1);
        user1.setName("a");
        User user2 = new User(2);
        user2.setName("b");
        User[] users = {user1, user2};
        Role role = new Role("user");
        role.setId(1);
        method.execute((s, usrs) -> {
            s.save(usrs[0]);
            s.save(usrs[1]);
            s.save(role);
            usrs[0].setRole(role);
            usrs[1].setRole(role);
            return null;
        }, users);
        Role rstl = method.execute((s, usrs) -> (Role) s.createQuery("from Role as r where r.name='user'").list().get(0), users);
        Assert.assertThat(rstl.getUsers().size(), Is.is(2));
    }

}