package lt.codeacademy.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.codeacademy.springmvc.entities.DeliveryInfo;

public interface CustomerDao extends JpaRepository<DeliveryInfo, Long> {
}
