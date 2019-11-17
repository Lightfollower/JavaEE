package adminConsole.controller;

import adminConsole.DAO.CategoryDAO;
import adminConsole.DAO.CategoryServiceLayer;
import adminConsole.persist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Stateful
@Named
public class AdminkaBean implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(AdminkaBean.class);

//    Репозиторий заменён на сервисный класс
    private CategoryServiceLayer categoryServiceLayer;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private CartRepository cartRepository;

//    Класс сущности заменён на класс представления
    private CategoryDAO categoryDAO;

    private Product product;
    private Order order;

    private List<CategoryDAO> categoriesList;
    private List<Product> productList;
    private List<Order> orderList;
    private List<Cart> cart;
    private List<Product> cartRes;

    public void preloadOrdersList(ComponentSystemEvent componentSystemEvent) {
        this.orderList = orderRepository.findAll();

    }

    public void preloadCategoryList(ComponentSystemEvent componentSystemEvent) {
        this.categoriesList = categoryServiceLayer.findAll();
    }

    public void preloadProductList(ComponentSystemEvent componentSystemEvent) {
        this.productList = productRepository.findAll();
    }

    public void preloadCart(ComponentSystemEvent componentSystemEvent) {
        this.cart = cartRepository.findByOrderId(order.getId());
    }

    public CategoryDAO getCategory() {
        return categoryDAO;
    }

    public Product getProduct() {
        return product;
    }

    public void setCategory(CategoryDAO category) {
        this.categoryDAO = category;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getCartRes() {
        return cartRes;
    }

    public void setCartRes(List<Product> cartRes) {
        this.cartRes = cartRes;
    }

    public List<CategoryDAO> getAllCategories() {
        return categoriesList;
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public List<Order> getAllOrders() {
        return orderList;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String createCategory() {
        this.categoryDAO = new CategoryDAO();
        return "/category.xhtml?faces-redirect=true";
    }

    public String createProduct() {
        this.product = new Product();
        this.categoryDAO = new CategoryDAO();
        return "/product.xhtml?faces-redirect=true";
    }

    public String saveCategory() {
        if (categoryDAO.getId() == null) {
            categoryServiceLayer.insert(categoryDAO);
        } else {
            categoryServiceLayer.update(categoryDAO);
        }
        return "/categories.xhtml?faces-redirect=true";
    }

//    public String saveProduct() throws RuntimeException {
//        if (product.getId() == null) {
//            product.setCategory(categoryServiceLayer.getCategoryByName(categoryDAO.getName()).get(0));
//            if (product.getCategory() == null)
//                throw new RuntimeException("Nonexistent category");
//            productRepository.insert(product);
//        } else {
//            product.setCategory(null);
//            product.setCategory(categoryServiceLayer.getCategoryByName(categoryDAO.getName()).get(0));
//            if (product.getCategory() == null)
//                throw new RuntimeException("Nonexistent category");
//            productRepository.update(product);
//        }
//        return "/products.xhtml?faces-redirect=true";
//    }

    public String saveOrder() {
        orderRepository.update(order);
        return "/orders.xhtml?faces-redirect=true";
    }

    public void deleteCategory(Category category) {
        logger.info("Deleting cat.");
        categoryServiceLayer.delete(category.getId());
    }

    public void deleteProduct(Product product) {
        logger.info("Deleting prod.");
        productRepository.delete(product.getId());
    }

    public String editCategory(CategoryDAO category) {
        this.categoryDAO = category;
        return "/category.xhtml?faces-redirect=true";
    }

//    public String editProduct(Product product) {
//        this.product = product;
//        this.categoryDAO = product.getCategory();
//
//        return "/product.xhtml?faces-redirect=true";
//    }

    public String editOrder(Order order) {
        this.order = order;
        return "/cart.xhtml?faces-redirect=true";
    }

    public String goToProducts() {
        return "products.xhtml?faces-redirect=true";
    }

    public String goToCategories() {
        return "categories.xhtml?faces-redirect=true";
    }

    public String goToOrders() {
        return "orders.xhtml?faces-redirect=true";
    }

    public String goToRoot() {
        return "adminka.xhtml?faces-redirect=true";
    }


}