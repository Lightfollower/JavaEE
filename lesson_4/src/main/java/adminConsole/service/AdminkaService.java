package adminConsole.service;

import adminConsole.persist.Category;
import adminConsole.persist.CategoryRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.List;

@Stateless
@WebService(endpointInterface = "adminConsole.service.AdminkaServiceWS", serviceName = "AdminService")
public class AdminkaService {

    @EJB
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void insert(Category category) {
        categoryRepository.insert(category);
    }
}
