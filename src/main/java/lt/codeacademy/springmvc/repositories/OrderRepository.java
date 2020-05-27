package lt.codeacademy.springmvc.repositories;

import lt.codeacademy.springmvc.entities.Customer;
import lt.codeacademy.springmvc.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByCustomer(Customer customer, Pageable pageable);
}
