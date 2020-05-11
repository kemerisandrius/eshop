package lt.codeacademy.springmvc.repositories;

import lt.codeacademy.springmvc.controller.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InternalProductsDao implements ProductsDao {
    private List<Product> products;

    public InternalProductsDao() {
        this.products = buildProducts();
    }

    private List<Product> buildProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Atsuktuvas");
        product1.setDescription("Puikiai atsuka");
        product1.setPrice(15.50);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Obuolys");
        product2.setDescription("Skanus");
        product2.setPrice(2.50);

        Product product3 = new Product();
        product3.setId(3L);
        product3.setTitle("Klaviatura");
        product3.setDescription("Labai gerai spaudosi");
        product3.setPrice(100.0);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        return products;
    }

    @Override
    public Optional<Product> getProduct(Long id) {

        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean deleteProduct(Long id) {
        List<Product> newList = products.stream()
                .filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());

        boolean deleted = newList.size() != products.size();
        products = newList;

        return deleted;
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Product updateProduct(Product product) {
        getProduct(product.getId()).ifPresent(existingProduct -> {
            existingProduct.setDescription(product.getDescription());
            existingProduct.setTitle(product.getTitle());
            existingProduct.setPrice(product.getPrice());
        });

        return product;
    }

    @Override
    public Product createProduct(Product product) {
        Long maxId = products.stream()
                .mapToLong(Product::getId)
                .max().orElse(0L);

        product.setId(maxId + 1);
        products.add(product);

        return product;
    }
}
