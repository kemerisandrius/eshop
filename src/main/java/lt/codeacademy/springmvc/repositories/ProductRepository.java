package lt.codeacademy.springmvc.repositories;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.springmvc.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//      @Query("SELECT p FROM Product p WHERE p.price = ?1")
//      List<Product> getProductsByPrice(Double price);

//      @Query(value = "SELECT * FROM Products WHERE price=?1", nativeQuery = true)
//      List<Product> getProductsByPrice(Double price);

        Page<Product> getAllByPrice(BigDecimal price, Pageable pageable);

}
