package adminConsole.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@Named
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

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

    public void insert(Product product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO products (prodName, cat_id) VALUES (?, ?);");
             PreparedStatement stmtCat = conn.prepareStatement(
                     "SELECT idcategory FROM categories Where catName = ?"
             )) {
            stmtCat.setString(1, product.getCategory());
            ResultSet resultSet = stmtCat.executeQuery();
            resultSet.next();

            stmt.setString(1, product.getName());
            stmt.setLong(2, resultSet.getLong(1));
            stmt.execute();
        }
    }

    public void update(Product product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE products SET prodName = ?, cat_id = ? WHERE (idProducts = ?);");
             PreparedStatement stmtCat = conn.prepareStatement(
                     "SELECT idcategory FROM categories Where catName = ?")) {
            stmtCat.setString(1, product.getCategory());
            ResultSet resultSet = stmtCat.executeQuery();
            resultSet.next();

            stmt.setString(1, product.getName());
            stmt.setLong(2, resultSet.getLong(1));
            stmt.setLong(3, product.getId());
            stmt.execute();
        }
    }

    public void delete(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM products WHERE (idProducts = ?);")) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }

    public Product findById(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM products where idProducts = ?");
             PreparedStatement stmtCat = conn.prepareStatement(
                     "SELECT catName FROM categories where idcategory = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String category;
                stmtCat.setLong(1, id);
                ResultSet rsCat = stmt.executeQuery();
//                category = new String(rsCat.getString(1));

                return new Product(rs.getLong(1), rs.getString(2), "ololo");
            }
        }
        return new Product(-1L, "", "");
    }


    public List<Product> findAll() throws SQLException {
        List<Product> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             Statement stmtCat = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from products");
            ResultSet rsCat = stmtCat.executeQuery("select * from categories");
            Map<Long, String> catMap = new HashMap<>();
            while (rsCat.next()) {
                catMap.put(rsCat.getLong(1), rsCat.getString(2));
            }
            while (rs.next()) {
                res.add(new Product(rs.getLong(1), rs.getString(2), catMap.get(rs.getLong(3))));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE `products` (\n" +
                    "  `idProducts` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `prodName` varchar(45) NOT NULL,\n" +
                    "  `cat_id` int(11) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`idProducts`),\n" +
                    "  UNIQUE KEY `idProducts_UNIQUE` (`idProducts`),\n" +
                    "  KEY `cat_id` (`cat_id`),\n" +
                    "  CONSTRAINT `cat_id` FOREIGN KEY (`cat_id`) REFERENCES `categories` (`idcategory`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci");
        }
    }
}