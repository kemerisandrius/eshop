package lt.codeacademy.springmvc.services;

import lt.codeacademy.springmvc.controller.Product;
import lt.codeacademy.springmvc.controller.ProductNotFoundException;
import lt.codeacademy.springmvc.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Product> getProductsByPrice(Double price, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber,1);
        return productRepository.getAllByPrice(price, pageable).getContent();
    }
}
