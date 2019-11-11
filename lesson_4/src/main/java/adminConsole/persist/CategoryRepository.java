package adminConsole.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ApplicationScoped
@Named
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

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

    public void insert(Category category) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO categories (catName) VALUES (?);")) {
            stmt.setString(1, category.getName());
            stmt.execute();
        }
    }

    public void update(Category category) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE categories SET catName = ? WHERE idcategory = ?")) {
            stmt.setString(1, category.getName());
            stmt.setLong(2, category.getId());
            stmt.execute();
        }
    }

    public void delete(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from categories where idcategory = ?;")) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }

    public Category findById(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM categories Where idcategory = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Category(rs.getLong(1), rs.getString(2));
            }
        }
        return new Category(-1L, "");
    }

    public List<Category> findAll() throws SQLException {
        List<Category> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM categories;");

            while (rs.next()) {
                res.add(new Category(rs.getLong(1), rs.getString(2)));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE `categories` (\n" +
                    "  `idcategories` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `catName` varchar(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`idcategories`),\n" +
                    "  UNIQUE KEY `idcategories_UNIQUE` (`idcategories`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci");
        }
    }
}
