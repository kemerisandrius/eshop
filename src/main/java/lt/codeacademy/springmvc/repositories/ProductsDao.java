package lt.codeacademy.springmvc.repositories;

import lt.codeacademy.springmvc.controller.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsDao {
    Optional<Product> getProduct(Long id);

    boolean deleteProduct(Long id);

    List<Product> getProducts();

    Product updateProduct(Product product);

    Product createProduct(Product product);
}
