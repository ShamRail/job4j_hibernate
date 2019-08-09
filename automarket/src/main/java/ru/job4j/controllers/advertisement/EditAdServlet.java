package ru.job4j.controllers.advertisement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.logic.AdvertisementLogic;
import ru.job4j.logic.Logic;
import ru.job4j.models.annotatedmodels.Advertisement;
import ru.job4j.models.annotatedmodels.Car;
import ru.job4j.utils.Encoder;
import ru.job4j.utils.FileUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;

public class EditAdServlet extends HttpServlet {

    private static final Logic<Advertisement> LOGIC = AdvertisementLogic.getInstance();

    private static final Logger LOG = LogManager.getLogger(EditAdServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Advertisement ad = LOGIC.getById(id);
        ad.setPhotoPath(Encoder.encode(ad.getPhotoPath()));
        req.setAttribute("ad", ad);
        req.setAttribute("id", req.getSession().getAttribute("id"));
        req.getRequestDispatcher("/WEB-INF/views/advertisement/editad.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.debug("###############################################");
        LOG.debug("Editing ad ...");
        LOG.debug("Read parameters ...");

        int id = Integer.parseInt(req.getParameter("id"));
        Advertisement old = LOGIC.getById(id);
        Advertisement ad = updateAdvertisement(req, old);
        Car car = readCar(req);
        ad.setCar(car);
        car.setAdvertisement(ad);

        LOG.debug("Reading finished");
        LOG.debug("Trying to save photo ...");

        savePhoto(req, ad);

        LOG.debug("Trying to update ad");

        LOGIC.update(id, ad);

        LOG.debug("Editing finished");

        LOG.debug("###############################################");
        resp.sendRedirect(String.format("%s/ads", req.getContextPath()));
    }

    private Advertisement updateAdvertisement(HttpServletRequest req, Advertisement old) {
        old.setTitle(req.getParameter("title"));
        old.setDescription(req.getParameter("description"));
        old.setDate(new Timestamp(Long.parseLong(req.getParameter("date"))));
        old.setPrice(Integer.parseInt(req.getParameter("price")));
        old.setStatus(true);
        return old;
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

    private String savePhoto(HttpServletRequest req, Advertisement ad) throws IOException, ServletException {
        Part filePart = req.getPart("photo");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String location = FileUtil.saveCarPhoto(
                filePart.getInputStream(), String.format("%s%s", ad.hashCode(), fileName)
        );
        String prevPath = req.getParameter("photoPath");
        String result;
        if (location == null) {
            ad.setPhotoPath(prevPath);
            result = prevPath;
        } else {
            ad.setPhotoPath(location);
            LOG.debug("Removing previous photo ...");
            FileUtil.removeFile(prevPath);
            result = location;
        }
        return result;
    }

}
