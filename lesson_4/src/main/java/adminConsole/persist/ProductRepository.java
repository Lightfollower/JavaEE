package adminConsole.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Named
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Transactional
    @PostConstruct
    public void init() {
    }

    @Transactional
    public void insert(Product product) {
        em.persist(product);
    }

    @Transactional
    public void update(Product product) {
        em.merge(product);
    }

    @Transactional
    public void delete(long id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
    }

    @Transactional
    public Product findById(long id) {
        return em.find(Product.class, id);
    }

    @Transactional
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
