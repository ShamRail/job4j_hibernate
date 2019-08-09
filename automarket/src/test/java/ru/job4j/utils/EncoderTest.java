package ru.job4j.utils;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;


public class EncoderTest {

    @Test
    public void testSlashEncoding() {
        Assert.assertThat(Encoder.encode("/something/"), Is.is("%2Fsomething%2F"));
    }

    @Test
    public void testBaclslashEncoding() {
        Assert.assertThat(Encoder.encode("\\something\\"), Is.is("%5Csomething%5C"));
    }

}