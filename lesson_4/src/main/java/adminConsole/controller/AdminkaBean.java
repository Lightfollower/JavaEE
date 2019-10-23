package adminConsole.controller;

import adminConsole.persist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
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
    private Order order;

    private List<Category> categoriesList;
    private List<Product> productList;
    private List<Order> orderList;

    public void preloadOrdersList(ComponentSystemEvent componentSystemEvent) {
        this.orderList = orderRepository.findAll();

    }

    public void preloadCategoryList(ComponentSystemEvent componentSystemEvent) {
        this.categoriesList = categoryRepository.findAll();
    }

    public void preloadProductList(ComponentSystemEvent componentSystemEvent) {
        this.productList = productRepository.findAll();
    }

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

    public List<Category> getAllCategories() {
        return categoriesList;
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public List<Order> getAllOrders() {
        return orderList;
    }

    public String createCategory() {
        this.category = new Category();
        return "/category.xhtml?faces-redirect=true";
    }

    public String createProduct() {
        this.product = new Product();
        return "/product.xhtml?faces-redirect=true";
    }

    public String saveCategory() {
        if (category.getId() == null) {
            categoryRepository.insert(category);
        } else {
            categoryRepository.update(category);
        }
        return "/adminka.xhtml?faces-redirect=true";
    }

    public String saveProduct() {
        if (product.getId() == null) {
            product.setCategory(categoryRepository.findByName(category.getName()));
            productRepository.insert(product);
        } else {
            product.setCategory(category);
            productRepository.update(product);
        }
        return "/adminka.xhtml?faces-redirect=true";
    }

    public void deleteCategory(Category category) {
        logger.info("Deleting cat.");
        categoryRepository.delete(category.getId());
    }

    public void deleteProduct(Product product) {
        logger.info("Deleting prod.");
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

    public String goToProducts(){
        return "products.xhtml?faces-redirect=true";
    }

    public String goToCategoryes(){
        return "categories.xhtml?faces-redirect=true";
    }

    public String goToOrders(){
        return "orders.xhtml?faces-redirect=true";
    }
}