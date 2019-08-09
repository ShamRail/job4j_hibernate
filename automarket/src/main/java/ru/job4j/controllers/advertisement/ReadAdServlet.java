package ru.job4j.controllers.advertisement;

import ru.job4j.logic.AdvertisementLogic;
import ru.job4j.logic.Logic;
import ru.job4j.models.annotatedmodels.Advertisement;
import ru.job4j.utils.Encoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReadAdServlet extends HttpServlet {

    private static final Logic<Advertisement> LOGIC = AdvertisementLogic.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Advertisement ad = LOGIC.getById(Integer.parseInt(req.getParameter("id")));
        ad.setPhotoPath(Encoder.encode(ad.getPhotoPath()));
        req.setAttribute("ad", ad);
        req.setAttribute("id", req.getSession().getAttribute("id"));
        req.getRequestDispatcher("/WEB-INF/views/advertisement/readad.jsp").forward(req, resp);
    }

}
