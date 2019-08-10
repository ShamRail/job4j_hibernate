package ru.job4j.utils.query;

import javax.servlet.http.HttpServletRequest;

public class RequestParser implements QueryParser<HttpServletRequest> {

    @Override
    public String parse(HttpServletRequest req) {

        StringBuilder sb = new StringBuilder();
        String free = req.getParameter("free");
        String withPhoto = req.getParameter("withPhoto");
        String sort = req.getParameter("sort");
        String mark = req.getParameter("mark");

        boolean pastedWhere = false;
        sb.append("from Advertisement as ad");
        if (free != null) {
            sb.append(" where ");
            pastedWhere = true;
            sb.append("ad.status = true and ");
        }
        if (withPhoto != null) {
            if (!pastedWhere) {
                sb.append(" where ");
                pastedWhere = true;
            }
            sb.append("ad.photoPath <> null and ");
        }
        if (mark != null) {
            if (!pastedWhere) {
                sb.append(" where ");
                pastedWhere = true;
            }
            sb.append(String.format("ad.car.mark = '%s' and ", mark));
        }

        if (sort != null) {
            sb.append(" order by ad.id desc");
        }

        String result = sb.toString();
        if (result.endsWith("and ")) {
            int lastSpace = result.lastIndexOf('a');
            result = result.substring(0, lastSpace - 1);
        }

        return result;
    }

}
