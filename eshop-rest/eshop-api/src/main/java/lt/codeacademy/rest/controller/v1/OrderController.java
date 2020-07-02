package lt.codeacademy.rest.controller.v1;

import java.util.Arrays;
import java.util.List;
import lt.codeacademy.rest.entities.Customer;
import lt.codeacademy.rest.entities.Order;
import lt.codeacademy.rest.entities.Product;
import lt.codeacademy.rest.services.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RestController("OrderController.v1")
@RequestMapping("/v1/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order buildOrder(@RequestBody Order order) {
        return orderService.buildOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }
}
