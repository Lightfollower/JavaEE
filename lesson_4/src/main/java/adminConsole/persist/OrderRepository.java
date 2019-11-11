package adminConsole.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@Named
public class OrderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    @Inject
    private ServletContext servletContext;

    private Connection conn;

    @PostConstruct
    public void init() throws SQLException {
        String jdbcConnectionString = servletContext.getInitParameter("jdbcConnectionString");
        String username = servletContext.getInitParameter("username");
        String password = servletContext.getInitParameter("password");

        try {
            this.conn = DriverManager.getConnection(jdbcConnectionString, username, password);

        } catch (SQLException ex) {
            logger.error("", ex);
        }
    }


    public void update(Order order) throws SQLException {
    }

    public Order findById(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM products where idProducts = ?");
             PreparedStatement stmtCat = conn.prepareStatement(
                     "SELECT catName FROM categories where idcategory = ?");
             PreparedStatement stmtOrd = conn.prepareStatement(
                     "select status FROM orders Where idorders = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            ResultSet rsOrd;
            if (rs.next()) {
                String category;
                stmtCat.setLong(1, id);
                ResultSet rsCat = stmt.executeQuery();
                stmtOrd.setLong(1, rs.getLong(1));
                rsOrd = stmtOrd.executeQuery();
                return new Order(rs.getLong(1), rs.getString(2));
            }
        }
        return new Order(-1L, "");
    }

    public List<Order> findAll() throws SQLException {
        List<Order> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery("select * from clients");
            while (rs.next()) {
                res.add(new Order(rs.getLong(1), rs.getString(2)));
            }
        }
        return res;
    }

    public List<String> findProductListForOrderForm(String clientName) throws SQLException {
        long clientId;
        List<Long> resProdId = new ArrayList<>();
        List<String> resProd = new ArrayList<>();
        try (PreparedStatement stmtCli = conn.prepareStatement("SELECT idClients from clients WHERE cliName = ?");
             PreparedStatement stmtProdId = conn.prepareStatement("select prod_id from orders WHERE client_id = ?");
             PreparedStatement stmtProd = conn.prepareStatement("select prodName from products WHERE idProducts = ?")) {
            stmtCli.setString(1, clientName);
            ResultSet rsCli = stmtCli.executeQuery();
            rsCli.next();
            clientId = rsCli.getLong(1);

            ResultSet rsProdId;
            stmtProdId.setLong(1, clientId);
            rsProdId = stmtProdId.executeQuery();
            while (rsProdId.next()) {
                resProdId.add(rsProdId.getLong(1));
            }

            for (long l :
                    resProdId) {
                stmtProd.setLong(1, l);
                ResultSet rsProduct = stmtProd.executeQuery();
                rsProduct.next();
                resProd.add(rsProduct.getString(1));
            }
        }
        return resProd;
    }
}
