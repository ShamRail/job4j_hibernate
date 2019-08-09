package ru.job4j.controllers.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.logic.Logic;
import ru.job4j.logic.UserLogic;
import ru.job4j.models.annotatedmodels.Advertisement;
import ru.job4j.models.annotatedmodels.User;
import ru.job4j.utils.Encoder;
import ru.job4j.utils.FileUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Set;

public class EditUserServlet extends HttpServlet {

    private static final Logic<User> LOGIC = UserLogic.getInstance();

    private static final Logger LOG = LogManager.getLogger(EditUserServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("id");
        int id = Integer.parseInt(req.getParameter("id"));
        User user = LOGIC.getById(id);
        user.setPhotoPath(Encoder.encode(user.getPhotoPath()));
        req.setAttribute("user", user);
        req.setAttribute("ads", encode(user.getAds()));
        req.getRequestDispatcher("/WEB-INF/views/user/profileedit.jsp").forward(req, resp);
    }

    private Set<Advertisement> encode(Set<Advertisement> ads) {
        for (Advertisement ad : ads) {
            ad.setPhotoPath(Encoder.encode(ad.getPhotoPath()));
        }
        return ads;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.debug("###############################################");
        LOG.debug("Editing user ...");
        LOG.debug("Read parameters ...");

        int id = Integer.parseInt(req.getParameter("id"));
        User old = LOGIC.getById(id);

        updateUserFields(req, old);

        LOG.debug("Reading finished");
        LOG.debug("Trying to save photo ...");

        savePhoto(req, old);

        LOG.debug("Trying to update ad");
        LOGIC.update(id, old);
        LOG.debug("Editing finished");

        LOG.debug("###############################################");
        resp.sendRedirect(String.format("%s/ads", req.getContextPath()));
    }

    private void savePhoto(HttpServletRequest req, User old) throws IOException, ServletException {
        Part filePart = req.getPart("photo");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String location = FileUtil.saveCarPhoto(
                filePart.getInputStream(), String.format("%s%s", old.hashCode(), fileName)
        );
        String prevPath = req.getParameter("photoPath");
        if (location == null) {
            old.setPhotoPath(prevPath);
        } else {
            old.setPhotoPath(location);
            LOG.debug("Removing previous photo ...");
            FileUtil.removeFile(prevPath);
        }
    }

    private void updateUserFields(HttpServletRequest req, User old) {
        old.setName(req.getParameter("name"));
        old.setPassword(req.getParameter("password"));
        old.setEmail(req.getParameter("email"));
        old.setTelNumber(req.getParameter("telNumber"));
    }
}
