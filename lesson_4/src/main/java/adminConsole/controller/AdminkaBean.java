package adminConsole.controller;

import adminConsole.persist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
public class AdminkaBean implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(AdminkaBean.class);

    @Inject
    private CategoryRepository categoryRepository;
    @Inject
    private ProductRepository productRepository;
    @Inject
    private OrderRepository orderRepository;

    private Category category;
    private Product product;

    public Category getCategory() {
        return category;
    }

    public Product getProduct() {
        return product;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Category> getAllCategories() throws SQLException {
//        List<Category> categories = new ArrayList<>();
//        categories.add(new Category());
//        return categories;
        return categoryRepository.findAll();
    }

    public List<Product> getAllProducts() throws SQLException {
        return productRepository.findAll();
    }

    public String createCategory() {
        this.category = new Category();
        return "/category.xhtml?faces-redirect=true";
    }

    public String createProduct() {
        this.product = new Product();
        return "/product.xhtml?faces-redirect=true";
    }

    public String saveCategory() throws SQLException {
        if (category.getId() == null) {
            categoryRepository.insert(category);
        } else {
            categoryRepository.update(category);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public String saveProduct() throws SQLException {
        if (product.getId() == null) {
            productRepository.insert(product);
        } else {
            productRepository.update(product);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public void deleteCategory(Category category) throws SQLException {
        logger.info("Deleting Category.");
        categoryRepository.delete(category.getId());
    }

    public void deleteProduct(Product product) throws SQLException {
        logger.info("Deleting Product.");
        productRepository.delete(product.getId());
    }

    public String editCategory(Category category) {
        this.category = category;
        return "/category.xhtml?faces-redirect=true";
    }

    public String editProduct(Product product) {
        this.product = product;
        return "/product.xhtml?faces-redirect=true";
    }

    public String goToProducts() {
        return "products.xhtml?faces-redirect=true";
    }

    public String goToCategoryes() {
        return "categories.xhtml?faces-redirect=true";
    }

    public String goToOrders() {
        return "orders.xhtml?faces-redirect=true";
    }

}