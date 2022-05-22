package uz.project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.project.models.CustomResponse;
import uz.project.models.Order;
import uz.project.services.OrderService;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //adding product
    @PostMapping("/add_new_order")
    public ResponseEntity<Order> addingNewOrder(@RequestBody Order order) throws Exception {
        checkValidation(order);

        return ResponseEntity.ok(orderService.saveOrder(order));
    }

    private void checkValidation(Order order) throws Exception {
        if (order.getName() == null
                || order.getName().equals("")
                || order.getTotalPrice() == 0D
                || order.getLocation() == null
                || order.getProducts() == null
                || order.getUserId() == null)

            throw new Exception("Please fill all fields!");

    }

    //getting order with id
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) throws NotFoundException {
        var order = orderService.getOrderById(id);

        if (order == null)
            throw new NotFoundException("This order does not exist !");

        return ResponseEntity.ok(order);
    }


    //deleting order
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteOrder(@PathVariable Long id) throws Exception {

        if (id == null)
            throw new Exception("Please dont skip id!");

        if (orderService.doesOrderExist(id) && orderService.deleteOrder(id)) {
            return ResponseEntity.ok(new CustomResponse(HttpStatus.OK.value(), "Product is deleted successfully!"));

        } else {
            throw new NotFoundException("This order does not exist !");
        }
    }


    //updating order
    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) throws Exception {
        checkValidation(order);

        if (order.getId() == null || order.getId() == 0)
            return ResponseEntity.ok(orderService.saveOrder(order));

        return ResponseEntity.ok(orderService.updateOrder(order));
    }

    //check existence of order with id
    @GetMapping("/checking/{id}")
    public ResponseEntity<Boolean> doesOrderExistWithId(@PathVariable Long id) throws Exception {
        if (id == null || id == 0)
            throw new Exception("Please do not skip id!");

        return ResponseEntity.ok(orderService.doesExistOrder(id));
    }

    //getting all orders
    @GetMapping("/all")
    public ResponseEntity<List<Order>> findAllOrders() {

        try {
            var list = orderService.getAllOrders();

            if (list == null || list.isEmpty())
                ResponseEntity.ok(new ArrayList<>());
            return ResponseEntity.ok(list);

        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Order>> findAllOrdersOfUser(@PathVariable Long userId) {
        try {
            var list = orderService.getAllOrdersOfUser(userId);

            if (list == null || list.isEmpty())
                ResponseEntity.ok(new ArrayList<>());
            return ResponseEntity.ok(list);

        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    @GetMapping("/all/{userId}/check_orders")
    public ResponseEntity<Boolean> doesAnyOrderOfUserExist(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(orderService.doesOrderExistWithUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

}
