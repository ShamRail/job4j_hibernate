package ru.job4j.controllers.advertisement;

import ru.job4j.logic.AdvertisementLogic;
import ru.job4j.logic.Logic;
import ru.job4j.models.annotatedmodels.Advertisement;
import ru.job4j.utils.query.QueryParser;
import ru.job4j.utils.query.RequestParser;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FilterServlet extends HttpServlet {

    private static final QueryParser<HttpServletRequest> PARSER = new RequestParser();

    private static final Logic<Advertisement> LOGIC = AdvertisementLogic.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = PARSER.parse(req);
        if (query != null) {
            List<Advertisement> ads = LOGIC.getByQuery(query);
            req.setAttribute("ads", ads);
        } else {
            req.setAttribute("ads", null);
        }
        req.getRequestDispatcher("ads").forward(req, resp);
    }
}
