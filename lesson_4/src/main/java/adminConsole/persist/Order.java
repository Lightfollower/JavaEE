package adminConsole.persist;

public class Order {

    private Long id;

    private String name;

    public Order() {
    }

    public Order(Long id, String name, String orderName) {
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

    public Order getOrder() {
        return getOrder();
    }

}
