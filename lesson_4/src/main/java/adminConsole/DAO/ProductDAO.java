package adminConsole.DAO;

public class ProductDAO {

    private Long id;

    private String name;

    private CategoryDAO category;

    public ProductDAO() {
    }

    public ProductDAO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDAO getCategory() {
        return category;
    }

    public void setCategory(CategoryDAO category) {
        this.category = category;
    }
}