package lt.codeacademy.springmvc.repositories;

import java.util.List;
import lt.codeacademy.springmvc.entities.DeliveryInfo;
import lt.codeacademy.springmvc.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByDeliveryInfoIn(List<DeliveryInfo> deliveryInfoList, Pageable pageable);
}
