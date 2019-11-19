package adminConsole.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OrderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @TransactionAttribute
    @PostConstruct
    public void init() {
    }


    @TransactionAttribute
    public void update(Order order) {
        em.merge(order);
    }

    @TransactionAttribute
    public Order findById(long id) {
        return em.find(Order.class, id);
    }

    @TransactionAttribute
    public List<Order> findAll() {
        return em.createQuery("from Order", Order.class).getResultList();
    }

    @TransactionAttribute
    public List<Cart> findByOrderId(long id) {
        return em.createQuery("from Cart ", Cart.class).getResultList();
    }

}
