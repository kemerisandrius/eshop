package lt.codeacademy.springmvc.services;

import lt.codeacademy.springmvc.entities.Order;
import lt.codeacademy.springmvc.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
