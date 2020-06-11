package lt.codeacademy.springmvc.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.codeacademy.springmvc.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
