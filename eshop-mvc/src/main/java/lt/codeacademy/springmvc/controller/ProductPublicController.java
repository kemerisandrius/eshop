package lt.codeacademy.springmvc.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lt.codeacademy.springmvc.entities.Product;
import lt.codeacademy.springmvc.entities.User;
import lt.codeacademy.springmvc.services.CustomerService;
import lt.codeacademy.springmvc.services.ProductsService;

@Controller
@RequestMapping("/public/products")
public class ProductPublicController {

    private ProductsService productsService;
    private CustomerService customerService;

    public ProductPublicController(
        ProductsService productsService,
        CustomerService customerService) {
        this.productsService = productsService;
        this.customerService = customerService;
    }

    @GetMapping(path = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Product> getProductsAsJson() {
        return productsService.getAllProductsPaginated(0, null).getContent();
    }

    @GetMapping(path = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody List<Product> getProductsAsXml() {
        return productsService.getAllProductsPaginated(0, null).getContent();
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id, Model model, @AuthenticationPrincipal User user,
                             HttpSession session) {
        Product product = productsService.getProduct(id);
        model.addAttribute("product", product);
        model.addAttribute("user", user);

        return "product";
    }

    @GetMapping
    public String getProductsByPage(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(required = false) BigDecimal price,
        Model model,
        @AuthenticationPrincipal User user
    ) {
        Page<Product> products = productsService.getAllProductsPaginated(pageNumber, price);
        model.addAttribute("products", products.getContent());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("hasNextPage", products.hasNext());
        model.addAttribute("user", user);

        return "products";
    }
}
