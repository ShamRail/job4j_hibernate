package todolist.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.sql.Timestamp;


public class ItemTest {

    @Test
    public void serializationTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Item item = new Item(1, "one", new Timestamp(123), true);
        String json = objectMapper.writeValueAsString(item);
        String expect = "{\"id\":1,\"description\":\"one\",\"done\":true,\"creatingTime\":123}";
        Assert.assertThat(json, Is.is(expect));
    }

    @Test
    public void deserializationTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"id\":1,\"description\":\"one\",\"done\":true,\"creatingTime\":123}";
        Item result = objectMapper.readValue(json, Item.class);
        Item expect = new Item(1, "one", new Timestamp(123), true);
        Assert.assertThat(result, Is.is(expect));
    }

}