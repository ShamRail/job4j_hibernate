package ru.job4j.controllers.advertisement;

import ru.job4j.logic.AdvertisementLogic;
import ru.job4j.logic.Logic;
import ru.job4j.models.annotatedmodels.Advertisement;
import ru.job4j.utils.Encoder;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ShowAdsServlet extends HttpServlet {

    private static final Logic<Advertisement> ADVERTISEMENT_LOGIC = AdvertisementLogic.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            session = req.getSession();
            session.setAttribute("id", -1);
            session.setAttribute("name", "");
            session.setAttribute("password", "");
        }

        List<Advertisement> advertisementList = (List<Advertisement>) req.getAttribute("ads");
        if (advertisementList == null) {
            advertisementList = ADVERTISEMENT_LOGIC.read();
            req.setAttribute("ads", advertisementList);
        }
        req.setAttribute("paths", encode(advertisementList));
        req.setAttribute("id", session.getAttribute("id"));
        req.getRequestDispatcher("/WEB-INF/views/advertisement/ads.jsp").forward(req, resp);
    }

    private List<Advertisement> encode(List<Advertisement> ads) {
        for (Advertisement ad : ads) {
            ad.setPhotoPath(Encoder.encode(ad.getPhotoPath()));
        }
        return ads;
    }

}
