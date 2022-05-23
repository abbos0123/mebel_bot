package uz.project.services;

import org.springframework.stereotype.Service;
import uz.project.models.Order;
import uz.project.models.Product;
import uz.project.models.ProductCategory;
import uz.project.models.SpecialCategory;
import uz.project.repositories.OrderRepository;
import uz.project.repositories.ProductRepository;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //Adding order to database
    public Order saveOrder(Order order) {
        if (order == null) {
            return null;
        }
        return orderRepository.save(order);
    }

    //getting order by id
    public Order getOrderById(Long id) {
        if (!orderRepository.existsById(id))
            return null;
        return orderRepository.findOrderById(id);
    }

    //deleting  order
    public boolean deleteOrder(Long id) {

        if (!orderRepository.existsById(id))
            return false;

        try {
            var order = getOrderById(id);
            orderRepository.delete(order);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // updating order
    public Order updateOrder(Order order) {
        if (order == null) {
            return null;
        }
        return orderRepository.save(order);
    }

    // checking  existence with id
    public boolean doesExistOrder(Long id) {
        return orderRepository.existsById(id);
    }


    //getting all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //getting all orders of user
    public List<Order> getAllOrdersOfUser(Long userId) {
        return orderRepository.findOrdersByUserIdOrderByOrderTimeAsc(userId);
    }

    //checking existence of order
    public boolean doesOrderExist(long id){
        return orderRepository.existsOrderById(id);
    }

    //checking existence of users' order
    public boolean doesOrderExistWithUserId(long userId){
        return orderRepository.existsOrderByUserId(userId);
    }
}
