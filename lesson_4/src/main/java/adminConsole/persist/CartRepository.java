package adminConsole.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@PermitAll
public class CartRepository {

    private static final Logger logger = LoggerFactory.getLogger(CartRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @TransactionAttribute
    @PostConstruct
    public void init() {
    }


    @TransactionAttribute
    public void update(Cart cart) {
        em.merge(cart);
    }

    @TransactionAttribute
    public Cart findById(long id) {
        return em.find(Cart.class, id);
    }

    @TransactionAttribute
    public List<Cart> findAll() {
        return em.createQuery("from Cart ", Cart.class).getResultList();
    }

    @TransactionAttribute
    public List<Cart> findByOrderId(long id){
        return em.createQuery("SELECT c FROM Cart c WHERE c.order.id LIKE :name").setParameter("name", id).getResultList();
    }
}
