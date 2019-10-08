package lesson_2.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>the main cervelat lives here</h1>");
        resp.getWriter().println("<a href=/webapp/cart>Cart</a> ");
        resp.getWriter().println("<a href=/webapp/order>Order</a>");
        resp.getWriter().println("<a href=/webapp/catalog>Catalog</a>");
        resp.getWriter().println("<a href=/webapp/product>Product</a>");
    }
}
