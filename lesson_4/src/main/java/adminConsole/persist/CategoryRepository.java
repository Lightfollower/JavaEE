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
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Transactional
    @PostConstruct
    public void init() {
    }

    @Transactional
    public void insert(Category category) {
        em.persist(category);
    }

    @Transactional
    public void update(Category category) {
        em.merge(category);
    }

    @Transactional
    public void delete(long id) {
        Category category = em.find(Category.class, id);
        if (category != null) {
            em.remove(category);
        }
    }

    @Transactional
    public Category findById(long id) {
        return em.find(Category.class, id);
    }

    @Transactional
    public Category findByName(String name) {
        return em.find(Category.class, name);
    }

    @Transactional
    public List<Category> findAll() {
        Query query = em.createNamedQuery("Category.findAll", Category.class);
        List<Category> result = query.getResultList();
        return result;
    }
}
