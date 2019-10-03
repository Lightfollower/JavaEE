package ru.geekbrains.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.getWriter().println("<p>Header from filter</p>");
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.sendRedirect( "");
        filterChain.doFilter(servletRequest, servletResponse);
        servletResponse.getWriter().println("<p>Footer from filter</p>");
    }

    @Override
    public void destroy() {

    }
}
