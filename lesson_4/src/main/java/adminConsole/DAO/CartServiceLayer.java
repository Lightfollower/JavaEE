package adminConsole.DAO;

import adminConsole.persist.Cart;
import adminConsole.persist.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class CartServiceLayer implements Serializable {

    List<CartDAO> representations;
    CartDAO cartDAO;
    Cart cartEntity;

    @EJB
    CartRepository cartRepository;

    private static final Logger logger = LoggerFactory.getLogger(CartServiceLayer.class);


    public void update(CartDAO cart) {
        cartRepository.update(createEntityFromDAO(cart));
    }

    public Cart findById(long id) {
        return cartRepository.findById(id);
    }

    public List<CartDAO> findAll() {
        representations = new ArrayList<>();
        for (Cart c :
                cartRepository.findAll()) {
            representations.add(createDAOFromEntity(c));
        }
        return representations;
    }

    public List<CartDAO> findByOrderId(long id) {
        representations = new ArrayList<>();
        for (Cart c :
                cartRepository.findByOrderId(id)) {
            representations.add(createDAOFromEntity(c));
        }
        return representations;
    }

    private Cart createEntityFromDAO(CartDAO cart) {
        cartEntity = new Cart();
        cartEntity.setId(cart.getId());
        cartEntity.setClient(cart.getClient());
        cartEntity.setProduct(cart.getProduct());
        cartEntity.setOrder(cart.getOrder());
        return cartEntity;
    }

    private CartDAO createDAOFromEntity(Cart cart) {
        cartDAO = new CartDAO();
        cartDAO.setId(cart.getId());
        cartDAO.setClient(cart.getClient());
        cartDAO.setProduct(cart.getProduct());
        cartDAO.setOrder(cart.getOrder());
        return cartDAO;
    }
}
