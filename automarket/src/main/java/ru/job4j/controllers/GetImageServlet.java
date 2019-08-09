package ru.job4j.controllers;

import ru.job4j.logic.AdvertisementLogic;
import ru.job4j.logic.Logic;
import ru.job4j.models.annotatedmodels.Advertisement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GetImageServlet extends HttpServlet {

    private static final Logic<Advertisement> LOGIC = AdvertisementLogic.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        int id = Integer.parseInt(req.getParameter("id"));
//        String path = LOGIC.getById(id).getPhotoPath();

        String path = req.getParameter("path");

        //resp.setContentType("image/jpeg");

        try (InputStream stream = new FileInputStream(path);
             OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[4096];
            while (stream.read(buffer) != -1) {
                out.write(buffer);
            }
            out.flush();
        }

    }
}
