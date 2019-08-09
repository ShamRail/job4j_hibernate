package ru.job4j.controllers.user;

import ru.job4j.logic.Logic;
import ru.job4j.logic.UserLogic;
import ru.job4j.models.annotatedmodels.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveUserServlet extends HttpServlet {

    private static final Logic<User> LOGIC = UserLogic.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("id");
        LOGIC.delete(new User(id));
        req.getRequestDispatcher("signout").forward(req, resp);
    }

}
