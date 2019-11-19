package adminConsole.DAO;

import adminConsole.persist.Category;
import adminConsole.persist.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CategoryServiceLayer implements Serializable {
    List<CategoryDAO> representations;
    CategoryDAO categoryDAO;
    Category categoryEntity;

    @EJB
    private CategoryRepository categoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceLayer.class);

    public List<CategoryDAO> findAll() {
        representations = new ArrayList<>();
        for (Category c :
                categoryRepository.findAll()) {

            representations.add(createDAOFromEntity(c));
        }
        for (CategoryDAO c :
                representations) {
            System.out.println("ololo");
            System.out.println("ololo");
            System.out.println("ololo");
            System.out.println(c.getId());
            System.out.println(c.getName());
            System.out.println("ololo");
            System.out.println("ololo");
            System.out.println("ololo");
        }
        return representations;
    }

    public void insert(CategoryDAO category) {
        categoryRepository.insert(createEntityFromDAO(category));
    }

    public void update(CategoryDAO category) {
        categoryRepository.update(createEntityFromDAO(category));
    }

    public List<CategoryDAO> getCategoryByName(String name) {
        representations = new ArrayList<>();
        for (Category c :
                categoryRepository.getCategoryByName(name)) {
            representations.add(createDAOFromEntity(c));
        }
        return representations;
    }

    public void delete(long id) {
        categoryRepository.delete(id);
    }

    public CategoryDAO createDAOFromEntity(Category category){
        categoryDAO = new CategoryDAO();
        categoryDAO.setId(category.getId());
        categoryDAO.setName(category.getName());
        return categoryDAO;
    }

    public Category createEntityFromDAO(CategoryDAO category){
        categoryEntity = new Category();
        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());
        return categoryEntity;
    }

}
