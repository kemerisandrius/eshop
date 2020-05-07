package lt.codeacademy.springmvc.services;

import lt.codeacademy.springmvc.controller.Product;
import lt.codeacademy.springmvc.controller.ProductNotFoundException;
import lt.codeacademy.springmvc.repositories.ProductsDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    private ProductsDao productsDao;

    public ProductsService(ProductsDao productsDao) {
        this.productsDao = productsDao;
    }

    public Product getProduct(Long id) {

        return productsDao.getProduct(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " was not found"));
    }

    public List<Product> deleteProduct(Long id) {
        productsDao.deleteProduct(id);
        return productsDao.getProducts();
    }

    public Product createOrUpdateProduct(Product product) {

        if (product.getId() != null) {
            return productsDao.updateProduct(product);
        } else {
            return productsDao.createProduct(product);
        }
    }

    public List<Product> getAllProducts() {
        return productsDao.getProducts();
    }
}
