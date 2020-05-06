package lt.codeacademy.springmvc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    private List<Product> products;

    public ProductController() {
        this.products = buildProducts();
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Product product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " was not found"));

        model.addAttribute("product", product);
        return "productpage";
    }

    @GetMapping("/product/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        Product product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " was not found"));

        model.addAttribute("product", product);
        return "productform";
    }

    @GetMapping("/product")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "productform";
    }

    @PostMapping("/product")
    public String submitProduct(@ModelAttribute Product product) {
        List<Product> newProducts = products.stream()
                .filter(p -> !p.getId().equals(product.getId()))
                .collect(Collectors.toList());
        newProducts.add(product);
        products = newProducts;

        return "productpage";
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", products);
        return "productlist";
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
}
