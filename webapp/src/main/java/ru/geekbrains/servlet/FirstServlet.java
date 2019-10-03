package ru.geekbrains.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;

public class FirstServlet implements Servlet, Serializable {

    private transient ServletConfig servletConfig;

    private transient Connection conn;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
        this.conn = (Connection)this.servletConfig.getServletContext().getAttribute("dbConnection");
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getWriter().println("<h1>XYU</h1>");
        Boolean redirected = (boolean)servletRequest.getAttribute("forwarded");
        if(redirected != null && redirected){
            servletResponse.getWriter().println("<h2>request redirected</h2>");
        }
    }

    @Override
    public String getServletInfo() {
        return "First servlet";
    }

    @Override
    public void destroy() {

    }
}
