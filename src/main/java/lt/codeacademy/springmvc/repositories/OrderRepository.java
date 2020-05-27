package lt.codeacademy.springmvc.repositories;

import lt.codeacademy.springmvc.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
