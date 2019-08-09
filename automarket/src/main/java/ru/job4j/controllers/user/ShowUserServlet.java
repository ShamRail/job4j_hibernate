package ru.job4j.controllers.user;

import ru.job4j.logic.Logic;
import ru.job4j.logic.UserLogic;
import ru.job4j.models.annotatedmodels.Advertisement;
import ru.job4j.models.annotatedmodels.User;
import ru.job4j.utils.Encoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class ShowUserServlet extends HttpServlet {

    private static final Logic<User> LOGIC = UserLogic.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = LOGIC.getById(id);
        user.setPhotoPath(Encoder.encode(user.getPhotoPath()));
        req.setAttribute("user", user);
        req.setAttribute("ads", encode(user.getAds()));
        req.setAttribute("id", req.getSession().getAttribute("id"));
        req.getRequestDispatcher("/WEB-INF/views/user/profilenoedit.jsp").forward(req, resp);
    }

    private Set<Advertisement> encode(Set<Advertisement> ads) {
        for (Advertisement ad : ads) {
            ad.setPhotoPath(Encoder.encode(ad.getPhotoPath()));
        }
        return ads;
    }
}
