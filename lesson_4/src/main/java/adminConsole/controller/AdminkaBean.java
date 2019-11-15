package adminConsole.controller;

import adminConsole.persist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
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

    @Inject
    private CartRepository cartRepository;

    private Category category;
    private Product product;
    private Order order;

    private List<Category> categoriesList;
    private List<Product> productList;
    private List<Order> orderList;
    private List<Cart> cart;
    private List<Product> cartRes;

    public void preloadOrdersList(ComponentSystemEvent componentSystemEvent) {
        this.orderList = orderRepository.findAll();

    }

    public void preloadCategoryList(ComponentSystemEvent componentSystemEvent) {
        this.categoriesList = categoryRepository.findAll();
    }

    public void preloadProductList(ComponentSystemEvent componentSystemEvent) {
        this.productList = productRepository.findAll();
    }

    public void preloadCart(ComponentSystemEvent componentSystemEvent) {
        this.productList = productRepository.findAll();
        this.cart = cartRepository.findAll();
        this.cartRes = new ArrayList<>();
        for (Cart c :
                cart) {
            if (c.getOrder().getId() == order.getId()) {
                this.cartRes.add(c.getProduct());
            }
        }
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

    public List<Product> getCartRes() {
        return cartRes;
    }

    public void setCartRes(List<Product> cartRes) {
        this.cartRes = cartRes;
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
        this.category = new Category();
        return "/category.xhtml?faces-redirect=true";
    }

    public String createProduct() {
        this.product = new Product();
        this.category = new Category();
        return "/product.xhtml?faces-redirect=true";
    }

    public String saveCategory() {
        if (category.getId() == null) {
            categoryRepository.insert(category);
        } else {
            categoryRepository.update(category);
        }
        return "/categories.xhtml?faces-redirect=true";
    }

    public String saveProduct() throws Exception {
        if (product.getId() == null) {
            for (Category c :
                    categoriesList) {
                if (c.getName().equals(category.getName())) {
                    product.setCategory(c);
                }
            }
            if (product.getCategory() == null)
                throw new Exception("ololo");
            productRepository.insert(product);
        } else {
            product.setCategory(null);
            for (Category c:
                    categoriesList) {
                if(c.getName().equals(category.getName())){
                    product.setCategory(c);

                }
            }
            if (product.getCategory() == null)
                throw new Exception("ololo");
            productRepository.update(product);
        }
        return "/products.xhtml?faces-redirect=true";
    }

    public String saveOrder() {
        orderRepository.update(order);
        return "/orders.xhtml?faces-redirect=true";
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
        this.category = product.getCategory();

        return "/product.xhtml?faces-redirect=true";
    }

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