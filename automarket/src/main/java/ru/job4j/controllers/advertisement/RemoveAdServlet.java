package ru.job4j.controllers.advertisement;

import ru.job4j.logic.AdvertisementLogic;
import ru.job4j.logic.Logic;
import ru.job4j.models.annotatedmodels.Advertisement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveAdServlet extends HttpServlet {

    private static final Logic<Advertisement> LOGIC = AdvertisementLogic.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Advertisement ad = new Advertisement();
        ad.setId(id);
        LOGIC.delete(ad);
    }
}
