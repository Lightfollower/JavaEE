package adminConsole.rest_service;

import adminConsole.DAO.CategoryDAO;
import adminConsole.DAO.CategoryServiceLayer;
import adminConsole.DAO.ProductDAO;
import adminConsole.DAO.ProductServiceLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@PermitAll
public class AdminkaServiceImpl implements AdminkaServiceRest {
    private static final Logger logger = LoggerFactory.getLogger(AdminkaServiceImpl.class);

    @EJB
    ProductServiceLayer productServiceLayer;

    @EJB
    CategoryServiceLayer categoryServiceLayer;

    @Override
    public void addProduct(ProductDAO product) {
        productServiceLayer.insert(product);
//        productServiceLayer.insert(new ProductDAO());
    }

    @Override
    public void deleteProductById(Long id) {
        productServiceLayer.delete(id);
    }

    @Override
    public void addCategory(CategoryDAO category) {
        categoryServiceLayer.insert(category);
    }

    @Override
    public ProductDAO getProductById(Long id) {
        return productServiceLayer.findProductById(id);
    }

    @Override
    public ProductDAO getProductByName(String name) {
        return productServiceLayer.findProductByName(name);
    }

    @Override
    public List<ProductDAO> findAll() {
        logger.info("findAll() invocation");
        return productServiceLayer.findAll();
    }

    @Override
    public List<ProductDAO> findByCatId(Long id) {
        return productServiceLayer.findProductByCatId(id);
    }
}
