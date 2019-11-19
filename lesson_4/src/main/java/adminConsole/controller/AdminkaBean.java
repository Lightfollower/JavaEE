package adminConsole.controller;

import adminConsole.DAO.*;
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
    private CategoryServiceLayer categoryServiceLayer;

    @Inject
    private ProductServiceLayer productServiceLayer;

    @Inject
    private OrderServiceLayer orderServiceLayer;

    @Inject
    private CartServiceLayer cartServiceLayer;

    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;

    private List<CategoryDAO> categoriesList;
    private List<ProductDAO> productList;
    private List<OrderDAO> orderList;
    private List<CartDAO> cart;
    private List<ProductDAO> cartRes;

    public void preloadOrdersList(ComponentSystemEvent componentSystemEvent) {
        this.orderList = orderServiceLayer.findAll();

    }

    public void preloadCategoryList(ComponentSystemEvent componentSystemEvent) {

        this.categoriesList = categoryServiceLayer.findAll();
    }

    public void preloadProductList(ComponentSystemEvent componentSystemEvent) {
        this.productList = productServiceLayer.findAll();
    }

    public void preloadCart(ComponentSystemEvent componentSystemEvent) {
        this.cart = cartServiceLayer.findByOrderId(orderDAO.getId());
    }

    public CategoryDAO getCategory() {
        return categoryDAO;
    }

    public ProductDAO getProduct() {
        return productDAO;
    }

    public void setCategory(CategoryDAO category) {
        this.categoryDAO = category;
    }

    public void setProduct(ProductDAO product) {
        this.productDAO = product;
    }

    public OrderDAO getOrder() {
        return orderDAO;
    }

    public void setOrder(OrderDAO order) {
        this.orderDAO = order;
    }

    public List<ProductDAO> getCartRes() {
        return cartRes;
    }

    public void setCartRes(List<ProductDAO> cartRes) {
        this.cartRes = cartRes;
    }

    public List<CategoryDAO> getAllCategories() {
        for (CategoryDAO c :
                categoriesList) {
            System.out.println("ololo");
            System.out.println("ololo");
            System.out.println("ololo");
            System.out.println(c.getName());
            System.out.println("ololo");
            System.out.println("ololo");
            System.out.println("ololo");
        }
        return categoriesList;
    }

    public List<ProductDAO> getAllProducts() {
        return productList;
    }

    public List<OrderDAO> getAllOrders() {
        return orderList;
    }

    public List<CartDAO> getCart() {
        return cart;
    }

    public String createCategory() {
        this.categoryDAO = new CategoryDAO();
        return "/category.xhtml?faces-redirect=true";
    }

    public String createProduct() {
        this.productDAO = new ProductDAO();
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

    public String saveProduct() throws RuntimeException {
        if (productDAO.getId() == null) {
            productDAO.setCategory(categoryServiceLayer.getCategoryByName(categoryDAO.getName()).get(0));
            if (productDAO.getCategory() == null)
                throw new RuntimeException("Nonexistent category");
            productServiceLayer.insert(productDAO);
        } else {
            productDAO.setCategory(null);
            productDAO.setCategory(categoryServiceLayer.getCategoryByName(categoryDAO.getName()).get(0));
            if (productDAO.getCategory() == null)
                throw new RuntimeException("Nonexistent category");
            productServiceLayer.update(productDAO);
        }
        return "/products.xhtml?faces-redirect=true";
    }

    public String saveOrder() {
        orderServiceLayer.update(orderDAO);
        return "/orders.xhtml?faces-redirect=true";
    }

    public void deleteCategory(CategoryDAO category) {
        logger.info("Deleting cat.");
        categoryServiceLayer.delete(category.getId());
    }

    public void deleteProduct(ProductDAO product) {
        logger.info("Deleting prod.");
        productServiceLayer.delete(product.getId());
    }

    public String editCategory(CategoryDAO category) {
        this.categoryDAO = category;
        return "/category.xhtml?faces-redirect=true";
    }

    public String editProduct(ProductDAO product) {
        this.productDAO = product;
        this.categoryDAO = product.getCategory();

        return "/product.xhtml?faces-redirect=true";
    }

    public String editOrder(OrderDAO order) {
        this.orderDAO = order;
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