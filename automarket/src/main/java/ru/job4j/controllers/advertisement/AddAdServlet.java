package ru.job4j.controllers.advertisement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.controllers.user.SignupServlet;
import ru.job4j.logic.AdvertisementLogic;
import ru.job4j.logic.Logic;
import ru.job4j.logic.UserLogic;
import ru.job4j.models.annotatedmodels.Advertisement;
import ru.job4j.models.annotatedmodels.Car;
import ru.job4j.models.annotatedmodels.User;
import ru.job4j.utils.FileUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

public class AddAdServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(SignupServlet.class.getName());

    private static final Logic<User> USER_LOGIC = UserLogic.getInstance();

    private static final Logic<Advertisement> ADVERTISEMENT_LOGIC = AdvertisementLogic.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("id", req.getSession().getAttribute("id"));
        req.getRequestDispatcher("/WEB-INF/views/advertisement/createad.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.debug("###############################################");
        LOG.debug("Saving ad ...");
        LOG.debug("Read parameters ...");

        Advertisement ad = readAdvertisement(req);
        Car car = readCar(req);
        car.setAdvertisement(ad);
        ad.setCar(car);

        LOG.debug("Reading finished");
        LOG.debug("Trying to save photo ...");

        ad.setPhotoPath(savePhoto(req, ad));

        LOG.debug("Photo saved");
        LOG.debug("Trying add ad to user");

        updateUser(req, ad);

        LOG.debug("User updated");

        resp.sendRedirect(String.format("%s/ads", req.getContextPath()));
    }


    private Advertisement readAdvertisement(HttpServletRequest req) {
        Advertisement ad = new Advertisement();
        ad.setTitle(req.getParameter("title"));
        ad.setDescription(req.getParameter("description"));
        ad.setDate(new Timestamp(Long.parseLong(req.getParameter("date"))));
        ad.setPrice(Integer.parseInt(req.getParameter("price")));
        ad.setStatus(true);
        return ad;
    }

    private Car readCar(HttpServletRequest req) {
        Car car = new Car();
        car.setMark(req.getParameter("mark"));
        car.setModel(req.getParameter("model"));
        car.setEngineType(req.getParameter("engine-type"));
        car.setEngineVolume(req.getParameter("engine-volume"));
        car.setUsed(Boolean.parseBoolean(req.getParameter("used")));
        car.setManufactureYear(Long.parseLong(req.getParameter("manufacture-year")));
        car.setTransmission(req.getParameter("transmission"));
        car.setBody(req.getParameter("body"));
        return car;
    }

    private void updateUser(HttpServletRequest req, Advertisement ad) {
        int id = (Integer) req.getSession().getAttribute("id");
        User user = USER_LOGIC.getById(id);
        Set<Advertisement> ads = user.getAds() != null ? user.getAds() : new LinkedHashSet<>();
        ads.add(ad);

        user.setAds(ads);
        ad.setUser(user);

        USER_LOGIC.update(id, user);
    }

    private String savePhoto(HttpServletRequest req, Advertisement ad) throws IOException, ServletException {
        Part filePart = req.getPart("photo");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        return FileUtil.saveCarPhoto(
                filePart.getInputStream(), String.format("%s%s", ad.hashCode(), fileName)
        );
    }

}
