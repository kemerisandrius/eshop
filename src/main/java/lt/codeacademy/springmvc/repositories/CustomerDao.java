package lt.codeacademy.springmvc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.codeacademy.springmvc.controller.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByUserName(String userName);
}
