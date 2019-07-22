package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import todolist.models.Item;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Items {

    public List<Item> items;

    public static void main(String[] args) throws JsonProcessingException {
        Item item = new Item(1, "2", new Timestamp(123), false);
        List<Item> list = new CopyOnWriteArrayList<Item>();
        list.add(item);
        Items items = new Items();
        items.items = list;
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(items);
        System.out.println(json);
    }

}

