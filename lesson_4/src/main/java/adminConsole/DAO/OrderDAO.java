package adminConsole.DAO;

import adminConsole.persist.Client;

import javax.ejb.Stateless;

@Stateless
public class OrderDAO {
    private Long id;

    private Client client;

    private String status;

    public OrderDAO() {
    }

    public OrderDAO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}