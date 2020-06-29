package lt.codeacademy.rest.services;

import java.util.List;
import lt.codeacademy.rest.entities.Order;
import lt.codeacademy.rest.repositories.OrderRepository;
import lt.codeacademy.rest.services.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order buildOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order was not found"));
    }
}
