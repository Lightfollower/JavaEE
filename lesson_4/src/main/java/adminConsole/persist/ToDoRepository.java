package adminConsole.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

//@ApplicationScoped
//@Named
public class ToDoRepository {

    private static final Logger logger = LoggerFactory.getLogger(ToDoRepository.class);

//    @PersistenceContext(unitName = "ds")
    private EntityManager em;

//    @Transactional
//    @PostConstruct
    public void init() {
        System.out.println();
        System.out.println();
        System.out.println("start init");
        System.out.println();
        System.out.println();
        List empty = this.findAll();
        if (empty.isEmpty()) {
            System.out.println();
            System.out.println();
            System.out.println(empty);
            System.out.println();
            System.out.println();
            this.insert(new ToDo(1L, "First", LocalDate.now()));
            System.out.println();
            System.out.println();
            System.out.println("continue fill");
            System.out.println();
            System.out.println();
//            this.insert(new ToDo(-1L, "Second", LocalDate.now().plusDays(1)));
//            this.insert(new ToDo(-1L, "Third", LocalDate.now().plusDays(2)));
            System.out.println();
            System.out.println();
            System.out.println("pjgjkytyj");
            System.out.println();
            System.out.println();
        }
    }

//    @Transactional
    public void insert(ToDo toDo) {
        System.out.println();
        System.out.println();
        System.out.println("start inserting");
        System.out.println();
        System.out.println();
        em.persist(toDo);
        System.out.println();
        System.out.println();
        System.out.println("inserted");
        System.out.println();
        System.out.println();
    }

//    @Transactional
    public void update(ToDo toDo) {
        em.merge(toDo);
    }

//    @Transactional
    public void delete(long id) {
        ToDo toDo = em.find(ToDo.class, id);
        if (toDo != null) {
            em.remove(toDo);
        }
    }

//    @Transactional
    public ToDo findById(long id) {
        return em.find(ToDo.class, id);
    }

//    @Transactional
    public List<ToDo> findAll() {
        System.out.println();
        System.out.println();
        System.out.println("muractional");
        System.out.println();
        System.out.println();
        return em.createQuery("from ToDo", ToDo.class).getResultList();
    }
}