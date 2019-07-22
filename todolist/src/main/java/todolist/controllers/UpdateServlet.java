package todolist.controllers;

import todolist.models.Item;
import todolist.services.DBStore;
import todolist.services.Store;
import utils.StringConverter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateServlet extends HttpServlet {

    private final Store store = DBStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = StringConverter.convert(req.getReader());
        String[] data = input.split("\\s+");
        Item item = new Item();
        item.setId(Integer.parseInt(data[0]));
        item.setDone(Boolean.parseBoolean(data[1]));
        store.update(item);
    }
}
