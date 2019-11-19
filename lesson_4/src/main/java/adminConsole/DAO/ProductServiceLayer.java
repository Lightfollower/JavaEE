package adminConsole.DAO;

import adminConsole.persist.Product;
import adminConsole.persist.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductServiceLayer implements Serializable {
    List<ProductDAO> representations;
    ProductDAO productDAO;
    Product productEntity;

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryServiceLayer categoryServiceLayer;

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceLayer.class);

    public List<ProductDAO> findAll() {
        representations = new ArrayList<>();
        for (Product p :
                productRepository.findAll()) {
            representations.add(createDAOFromEntity(p));
        }
        return representations;
    }

    public void insert(ProductDAO product) {
        productRepository.insert(createEntityFromDAO(product));
    }

    public void update(ProductDAO product) {
        productRepository.update(createEntityFromDAO(product));
    }

//    public List<ProductDAO> getCategoryByName(String name) {
//        representations = new ArrayList<>();
//        for (Category c :
//                categoryRepository.getCategoryByName(name)) {
//            representations.add(createDAOFromEntity(c));
//        }
//        return representations;
//    }

    public void delete(long id) {
        productRepository.delete(id);
    }

    private ProductDAO createDAOFromEntity(Product product){
        productDAO = new ProductDAO();
        productDAO.setId(product.getId());
        productDAO.setName(product.getName());
        productDAO.setCategory(categoryServiceLayer.createDAOFromEntity(product.getCategory()));
        return productDAO;
    }

    private Product createEntityFromDAO(ProductDAO product){
        productEntity = new Product();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setCategory(categoryServiceLayer.createEntityFromDAO(product.getCategory()));
        return productEntity;
    }

}
