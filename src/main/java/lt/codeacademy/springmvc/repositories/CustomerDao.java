package lt.codeacademy.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.codeacademy.springmvc.entities.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {
}
