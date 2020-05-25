package lt.codeacademy.springmvc.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lt.codeacademy.springmvc.controller.ProductNotFoundException;
import lt.codeacademy.springmvc.entities.Product;
import lt.codeacademy.springmvc.repositories.ProductRepository;

@Service
public class ProductsService {

    private ProductRepository productRepository;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " was not found"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product createOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Page<Product> getAllProductsPaginated(int pageNumber, BigDecimal price) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        if (price != null) {
            return productRepository.getAllByPrice(price, pageable);
        }
        return productRepository.findAll(pageable);
    }
}
