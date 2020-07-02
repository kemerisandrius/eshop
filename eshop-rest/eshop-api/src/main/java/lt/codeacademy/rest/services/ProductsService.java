package lt.codeacademy.rest.services;

import lt.codeacademy.rest.entities.Product;
import lt.codeacademy.rest.repositories.ProductRepository;
import lt.codeacademy.rest.services.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductsService {

    private ProductRepository productRepository;
    private FileStorageService fileStorageService;

    public ProductsService(
            ProductRepository productRepository,
            FileStorageService fileStorageService
    ) {
        this.productRepository = productRepository;
        this.fileStorageService = fileStorageService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product, MultipartFile file) {
        if (file != null) {
            product.setFileName(file.getOriginalFilename());
            fileStorageService.storeFile(file);
        }
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " was not found"));
    }

    public Page<Product> getProductsPaginated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageable);
    }
}
