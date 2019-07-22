package todolist.services;

import todolist.models.Item;

import java.util.List;

public interface Store {

    void create(Item item);

    List<Item> findAll();

    Item findById(int id);

    void update(Item item);

    void delete(Item item);

}
