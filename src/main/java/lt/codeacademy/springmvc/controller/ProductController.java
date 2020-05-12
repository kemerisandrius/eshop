package lt.codeacademy.springmvc.controller;

import lt.codeacademy.springmvc.services.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductsService productsService;

    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Product product = productsService.getProduct(id);
        model.addAttribute("product", product);
        return "productpage";
    }

    @GetMapping("/product/{id}")
    public String getUpdateProductForm(@PathVariable Long id, Model model) {
        Product product = productsService.getProduct(id);
        model.addAttribute("product", product);
        return "productform";
    }

    @GetMapping("/product")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "productform";
    }

    @GetMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable Long id, Model model) {
        productsService.deleteProduct(id);
        List<Product> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "productlist";
    }

    @GetMapping("/byPrice")
    public String getProductsByPrice(@RequestParam Double price, Model model) {
        List<Product> products = productsService.getProductsByPrice(price);
        model.addAttribute("products", products);
        return "productlist";
    }

    @PostMapping("/product")
    public String submitProduct(@ModelAttribute Product product, Model model) {
        Product newProduct = productsService.createOrUpdateProduct(product);
        model.addAttribute("product", newProduct);
        return "productpage";
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "productlist";
    }
}
