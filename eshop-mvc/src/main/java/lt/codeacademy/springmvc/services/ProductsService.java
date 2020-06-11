package lt.codeacademy.springmvc.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lt.codeacademy.springmvc.config.EshopConfigurationProperties;
import lt.codeacademy.springmvc.controller.ProductNotFoundException;
import lt.codeacademy.springmvc.entities.Product;
import lt.codeacademy.springmvc.repositories.ProductRepository;

@Service
public class ProductsService {

    private ProductRepository productRepository;

    @Autowired
    private EshopConfigurationProperties eshopConfigurationProperties;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product was not found"));

        if (eshopConfigurationProperties.getCountryOfOperation().equals("LT")) {
            Product lithuanianProduct = new Product();
            lithuanianProduct.setTitle(product.getTitle());
            lithuanianProduct.setDescription(product.getDescription());
            lithuanianProduct.setId(product.getId());
            BigDecimal priceWithPVM = product.getPrice().multiply(
                new BigDecimal(eshopConfigurationProperties.getPvmRate())
            );
            lithuanianProduct.setPrice(priceWithPVM);

            return lithuanianProduct;
        }
        return product;
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
