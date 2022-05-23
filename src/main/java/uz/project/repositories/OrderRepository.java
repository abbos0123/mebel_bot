package uz.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.project.models.Order;
import uz.project.models.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOrderById(Long id);

    Order findOrderByName(String username);

    boolean existsOrderById(Long id);

    boolean existsOrderByUserId(Long userId);

    List<Order> findOrdersByUserIdOrderByOrderTimeAsc(Long userId);

}
