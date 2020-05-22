package lt.codeacademy.springmvc.repositories;

import java.math.BigDecimal;
import java.util.List;
import lt.codeacademy.springmvc.controller.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//      @Query("SELECT p FROM Product p WHERE p.price = ?1")
//      List<Product> getProductsByPrice(Double price);

//      @Query(value = "SELECT * FROM Products WHERE price=?1", nativeQuery = true)
//      List<Product> getProductsByPrice(Double price);

        Page<Product> getAllByPrice(BigDecimal price, Pageable pageable);

}
