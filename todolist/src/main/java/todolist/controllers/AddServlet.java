package todolist.controllers;

import todolist.models.Item;
import todolist.services.DBStore;
import todolist.services.Store;
import utils.ItemParser;
import utils.StringConverter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {

    private final Store store = DBStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Item item = ItemParser.parse(StringConverter.convert(req.getReader()));
        store.create(item);
    }
}
