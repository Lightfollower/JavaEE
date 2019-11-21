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
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @TransactionAttribute
    @PostConstruct
    public void init() {
    }

    @TransactionAttribute
    public void insert(Product product) {
        em.persist(product);
    }

    @TransactionAttribute
    public void update(Product product) {
        em.merge(product);
    }

    @TransactionAttribute
    public void delete(long id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
    }

    @TransactionAttribute
    public Product findById(long id) {
        return em.find(Product.class, id);
    }

    @TransactionAttribute
    public List<Product> findByName(String name) {
        return em.createQuery("SELECT c FROM Product c WHERE c.name LIKE :name").setParameter("name", name).getResultList();
    }

    @TransactionAttribute
    public List<Product> findByCatId(long id) {
        return em.createQuery("SELECT c FROM Product c WHERE c.category.id LIKE :id").setParameter("id", id).getResultList();
    }

    @TransactionAttribute
    public List<Product> findAll() {
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
//        Root<Product> c = query.from(Product.class);
//        ParameterExpression<Integer> p = criteriaBuilder.parameter(Integer.class);
//        Predicate condition = criteriaBuilder.gt(c.get(Product_.id), p);
//        query.select(c).where(condition);
//        TypedQuery<Product> q = em.createQuery(query);
//        q.setParameter(p,1);
//        List<Product> products = q.getResultList();

//        return products;
        return em.createQuery("from Product", Product.class).getResultList();
    }

}
