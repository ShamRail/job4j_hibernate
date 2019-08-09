package ru.job4j.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.annotatedmodels.User;
import ru.job4j.persistance.DAO;
import ru.job4j.persistance.UserDAO;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoginFilter implements Filter {

    private static final DAO<User> DAO = UserDAO.getInstance();

    private static final Logger LOG = LogManager.getLogger(LoginFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        LOG.debug("###############################################");
        LOG.debug("Validating user");
        if ("post".equalsIgnoreCase(request.getMethod())) {
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            LOG.debug("Making query to DB");
            List<User> users = DAO.getByQuery(String.format("from User where name = '%s' and password = '%s'", name, password));
            LOG.debug("Query executed. Retrieved {} users", users.size());
            if (users.isEmpty()) {
                response.sendRedirect(String.format("%s/signin", request.getContextPath()));
                LOG.debug("Incorrect name or password");
            } else {
                request.setAttribute("id", users.get(0).getId());
                request.setAttribute("name", name);
                request.setAttribute("password", password);
                filterChain.doFilter(request, response);
                LOG.debug("Validating complete");
            }
            LOG.debug("###############################################");
        } else {
            filterChain.doFilter(request, response);
        }

    }

}
