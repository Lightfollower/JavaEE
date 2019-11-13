package adminConsole.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Named
public class OrderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Transactional
    @PostConstruct
    public void init() {
    }


    @Transactional
    public void update(Orders orders) {
        em.merge(orders);
    }

    @Transactional
    public Orders findById(long id) {
        return em.find(Orders.class, id);
    }

    @Transactional
    public List<Orders> findAll() {
        return em.createQuery("from Orders", Orders.class).getResultList();
    }

    @Transactional
    public List<Cart> findByOrderId(long id) {
        return em.createQuery("select product_id FROM cart WHERE orders_id = :id").getResultList();
    }

}
