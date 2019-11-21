package adminConsole.rest_service;

import adminConsole.DAO.CategoryDAO;
import adminConsole.DAO.ProductDAO;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("product")
public interface AdminkaServiceRest {

    @POST
    @Path("addnewprod")
    @Produces(MediaType.APPLICATION_JSON)
    void addProduct(ProductDAO product);

    @GET
    @Path("{id}/deleteproduct")
    @Produces(MediaType.APPLICATION_JSON)
    void deleteProductById(@PathParam("id") Long id);

    @GET
    @Path("addnewcat")
    @Produces(MediaType.APPLICATION_JSON)
    void addCategory(CategoryDAO category);

    @GET
    @Path("{id}/getproductbyid")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDAO getProductById(@PathParam("id") Long id);

    @GET
    @Path("{name}/getproductbyName")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDAO getProductByName(@PathParam("name") String name);

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDAO> findAll();

    @GET
    @Path("{id}/productsbycatid")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDAO> findByCatId(@PathParam("id") Long id);
}
