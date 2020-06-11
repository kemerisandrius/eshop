package lt.codeacademy.springmvc.services;

import java.util.List;
import java.util.stream.Collectors;
import lt.codeacademy.springmvc.entities.DeliveryInfo;
import lt.codeacademy.springmvc.entities.Order;
import lt.codeacademy.springmvc.entities.User;
import lt.codeacademy.springmvc.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Order> getUserOrders(User user, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        List<DeliveryInfo> deliveryInfoList = user.getDeliveryInfoList();
        return orderRepository.findAllByDeliveryInfoIn(deliveryInfoList, pageable);
    }
}
