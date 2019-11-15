package adminConsole.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Named
public class CartRepository {

    private static final Logger logger = LoggerFactory.getLogger(CartRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Transactional
    @PostConstruct
    public void init() {
    }


    @Transactional
    public void update(Cart cart) {
        em.merge(cart);
    }

    @Transactional
    public Cart findById(long id) {
        return em.find(Cart.class, id);
    }

    @Transactional
    public List<Cart> findAll() {
        return em.createQuery("from Cart ", Cart.class).getResultList();
    }
}
