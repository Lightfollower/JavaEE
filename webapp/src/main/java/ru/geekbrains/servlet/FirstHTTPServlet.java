package ru.geekbrains.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//@WebServlet(urlPatterns = "/first/http/servlet")
public class FirstHTTPServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>XYU</h1>");
        for (Map.Entry<String, String[]> param :
                req.getParameterMap().entrySet() ) {
            resp.getWriter().printf("<p>%s = %s</p>", param.getKey(),
                    String.join( ", ",param.getValue()));
        }
        resp.getWriter().printf("<p>Context %s</p> ", req.getContextPath());
        resp.getWriter().printf("<p>Servlet path %s</p> ", req.getServletPath());
        resp.getWriter().printf("<p>Path info %s</p> ", req.getPathInfo());
        resp.getWriter().printf("<p>Query str %s</p>", req.getQueryString());
        resp.getWriter().printf("<p>Request url %s</p>", req.getRequestURL());
    }
}
