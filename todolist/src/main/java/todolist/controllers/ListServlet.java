package todolist.controllers;

import todolist.services.DBStore;
import todolist.services.Store;
import utils.ItemParser;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListServlet extends HttpServlet {

    private final Store store = DBStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = ItemParser.serialize(store.findAll());
        resp.getWriter().print(json);
    }
}
