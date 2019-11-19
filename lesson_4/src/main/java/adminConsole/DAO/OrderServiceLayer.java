package adminConsole.DAO;

import adminConsole.persist.Order;
import adminConsole.persist.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class OrderServiceLayer implements Serializable {

    List<OrderDAO> representations;
    OrderDAO orderDAO;
    Order orderEntity;

    @EJB
    private OrderRepository orderRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceLayer.class);

    public List<OrderDAO> findAll() {
        representations = new ArrayList<>();
        for (Order c :
                orderRepository.findAll()) {
            representations.add(createDAOFromEntity(c));
        }
        return representations;
    }

    public void update(OrderDAO order) {
        orderRepository.update(createEntityFromDAO(order));
    }

//    public List<OrderDAO> getCategoryByName(String name) {
//        representations = new ArrayList<>();
//        for (Category c :
//                categoryRepository.getCategoryByName(name)) {
//            representations.add(createDAOFromEntity(c));
//        }
//        return representations;
//    }


    private OrderDAO createDAOFromEntity(Order order){
        orderDAO = new OrderDAO();
        orderDAO.setId(order.getId());
        orderDAO.setClient(order.getClient());
        orderDAO.setStatus(order.getStatus());
        return orderDAO;
    }

    private Order createEntityFromDAO(OrderDAO order){
        orderEntity = new Order();
        orderEntity.setId(order.getId());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setClient(order.getClient());
        return orderEntity;
    }

}
