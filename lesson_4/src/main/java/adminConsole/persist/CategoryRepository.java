package adminConsole.persist;

import adminConsole.controller.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @TransactionAttribute
    @PostConstruct
    public void init() {
    }

    @TransactionAttribute
    public void insert(Category category) {
        em.persist(category);
    }

    @TransactionAttribute
    public void update(Category category) {
        em.merge(category);
    }

    @TransactionAttribute
    public void delete(long id) {
        Category category = em.find(Category.class, id);
        if (category != null) {
            em.remove(category);
        }
    }

    @TransactionAttribute
    public Category findById(long id) {
        return em.find(Category.class, id);
    }

    @TransactionAttribute
    public Category findByName(String name) {
        return em.find(Category.class, name);
    }

    @TransactionAttribute
    @Interceptors({Interceptor.class})
    public List<Category> findAll() {
        Query query = em.createNamedQuery("Category.findAll", Category.class);
        List<Category> result = query.getResultList();
        return result;
    }

    @TransactionAttribute
    public List<Category> getCategoryByName(String name) {
        return em.createQuery("SELECT c FROM Category c WHERE c.name LIKE :name").setParameter("name", name).getResultList();
    }
}
