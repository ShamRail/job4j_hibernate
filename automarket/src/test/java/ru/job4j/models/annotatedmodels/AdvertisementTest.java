package ru.job4j.models.annotatedmodels;

import org.hamcrest.core.Is;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.utils.HibernateUtil;
import ru.job4j.utils.TemplateMethod;


public class AdvertisementTest {

    private static final SessionFactory FACTORY = HibernateUtil.getInstance();

    private TemplateMethod method = new TemplateMethod(FACTORY);

    @Test
    public void whenAddWithCar() {
        Car car = new Car();
        car.setMark("Audi");
        car.setModel("S7");
        Advertisement adv = new Advertisement();
        adv.setTitle("audi s7");
        Advertisement res = method.execute((s, c) -> {
            s.save(c);
            s.save(adv);
            adv.setCar(c);
            return (Advertisement) s.createQuery("from Advertisement as a where a.title='audi s7'").list().get(0);
        }, car);
        Assert.assertThat(res.getCar().getMark(), Is.is("Audi"));
    }

}