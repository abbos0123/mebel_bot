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


    public Order saveOrder(Order order) {
        if (order == null) {
            return null;
        }
        return orderRepository.save(order);
    }


    public Order getOrderById(Long id) {
        if (!orderRepository.existsById(id))
            return null;

        return orderRepository.findOrderById(id);
    }


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


    public Order updateOrder(Order order) {
        if (order == null) {
            return null;
        }
        return orderRepository.save(order);
    }


    public boolean doesExistOrder(Long id) {
        return orderRepository.existsById(id);
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    public List<Order> getAllOrdersOfUser(Long userId) {
        return orderRepository.findOrdersByUserIdOrderByOrderTimeAsc(userId);
    }


    public boolean doesOrderExist(long id){
        return orderRepository.existsOrderById(id);
    }


    public boolean doesOrderExistWithUserId(long userId){
        return orderRepository.existsOrderByUserId(userId);
    }
}
