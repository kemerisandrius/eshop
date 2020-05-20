package lt.codeacademy.springmvc.repositories;

import lt.codeacademy.springmvc.controller.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {

}
