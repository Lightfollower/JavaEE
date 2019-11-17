package adminConsole.DAO;

import adminConsole.persist.Category;
import adminConsole.persist.CategoryRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CategoryServiceLayer {

    @Inject
    private CategoryRepository categoryRepository;

    public List<CategoryDAO> findAll() {
        List<Category> result = categoryRepository.findAll();
//        Здесь будет создаваться представление на основе сущности.
        return new ArrayList<CategoryDAO>();
    }

    public void insert(CategoryDAO category) {
//        Здесь будет создаваться сущность на основе представления.
        categoryRepository.insert(new Category());
    }

    public void update(CategoryDAO category) {
        categoryRepository.update(new Category());
    }

    public List<CategoryDAO> getCategoryByName(String name) {
        return new ArrayList<CategoryDAO>();
    }

    public void delete(long id) {
        categoryRepository.delete(id);
    }

}
