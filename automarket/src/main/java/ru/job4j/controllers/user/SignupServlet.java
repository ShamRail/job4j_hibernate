package ru.job4j.controllers.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.logic.Logic;
import ru.job4j.logic.UserLogic;
import ru.job4j.models.annotatedmodels.User;
import ru.job4j.utils.FileUtil;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Paths;

public class SignupServlet extends HttpServlet {

    private static final Logic<User> USER_LOGIC = UserLogic.getInstance();

    private static final Logger LOG = LogManager.getLogger(SignupServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.debug("###############################################");
        LOG.debug("Saving user ...");
        LOG.debug("Read parameters ...");

        User user = readUser(req);

        LOG.debug("Try to save photo ...");

        user.setPhotoPath(savePhoto(req, user));

        LOG.debug("Reading complete");
        LOG.debug("Adding to DB ...");

        int id = USER_LOGIC.create(user);

        LOG.debug("Adding finished");
        LOG.debug("User saved");
        LOG.debug("###############################################");

        updateSession(req, user, id);

        resp.sendRedirect(String.format("%s/ads", req.getContextPath()));
    }

    private User readUser(HttpServletRequest req) {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setTelNumber(req.getParameter("telNumber"));
        return user;
    }

    private String savePhoto(HttpServletRequest req, User user) throws IOException, ServletException {
        Part filePart = req.getPart("photo");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        return FileUtil.saveProfilePhoto(
                filePart.getInputStream(), String.format("%s%s", user.hashCode(), fileName)
        );
    }

    private void updateSession(HttpServletRequest req, User user, int id) {
        HttpSession session = req.getSession();
        session.setAttribute("id", id);
        session.setAttribute("name", user.getName());
        session.setAttribute("password", user.getPassword());
    }

}
