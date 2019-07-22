package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import todolist.models.Item;
import java.io.IOException;
import java.util.List;

public class ItemParser {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Item parse(String json) throws IOException {
        return MAPPER.readValue(json, Item.class);
    }

    public static String serialize(List<Item> items) throws JsonProcessingException {
        return String.format("{\"items\":%s}", MAPPER.writeValueAsString(items));
    }


}
