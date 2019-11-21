package adminConsole.service;

import adminConsole.persist.Category;

import javax.ejb.Local;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@Local
@WebService
public interface AdminkaServiceWS {

    @WebMethod
    List<Category> findAll();

    @WebMethod
    void insert(Category category);
}
