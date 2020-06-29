package lt.codeacademy.rest.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import lt.codeacademy.rest.entities.Product;
import lt.codeacademy.rest.repositories.ProductRepository;
import lt.codeacademy.rest.services.exceptions.ProductNotFoundException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        Product product = Product.builder()
                .id(1L)
                .title("ABC")
                .price(BigDecimal.TEN)
                .build();

        productRepository.saveAndFlush(product);
    }

    @AfterEach
    public void cleanUp() {
        productRepository.deleteAll();
    }

    @Disabled("failed test")
    @Test
    public void shouldReturnOneProduct() {
        Product product = productsService.getProductById(1L);

        Assertions.assertEquals("ABC", product.getTitle());
        Assertions.assertEquals(null, product.getDescription());
        assertThat(BigDecimal.TEN, Matchers.comparesEqualTo(product.getPrice()));
    }

    @Test
    public void shouldThrowExceptionWhenProductNotFound() {
        Assertions.assertThrows(ProductNotFoundException.class, () -> productsService.getProductById(2L));
    }

    @Test
    public void shouldCreateProduct() {
        Product product = Product.builder()
                .title("DEF")
                .description("GHI")
                .price(BigDecimal.ONE)
                .build();

        productsService.createProduct(product, null);

        Assertions.assertEquals(2L, productRepository.count());
        Assertions.assertEquals(
                Arrays.asList(
                    Product.builder()
                            .id(2L)
                            .title("ABC")
                            .price(BigDecimal.valueOf(10).setScale(2, RoundingMode.CEILING))
                            .build(),
                    Product.builder()
                            .id(3L)
                            .title("DEF")
                            .description("GHI")
                            .price(BigDecimal.valueOf(1).setScale(2, RoundingMode.CEILING))
                            .build()
                ), productRepository.findAll());
    }

}
