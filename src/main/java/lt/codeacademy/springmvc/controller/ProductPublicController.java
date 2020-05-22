package lt.codeacademy.springmvc.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lt.codeacademy.springmvc.services.CustomerService;
import lt.codeacademy.springmvc.services.MyUser;
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

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id, Model model, @AuthenticationPrincipal MyUser user) {
        Product product = productsService.getProduct(id);
        model.addAttribute("product", product);
        model.addAttribute("user", user);
        return "productpage";
    }

    @GetMapping("/byPrice")
    public String getProductsByPrice(
        @RequestParam Double price,
        @RequestParam int pageNumber,
        Model model
    ) {
        List<Product> products = productsService.getProductsByPrice(price, pageNumber);
        model.addAttribute("products", products);
        return "productlist";
    }

    @GetMapping("/paginated")
    public String getProductsByPage(@RequestParam(defaultValue = "0") int pageNumber, Model model, @AuthenticationPrincipal MyUser user) {
        Page<Product> products = productsService.getAllProductsPaginated(pageNumber);
        model.addAttribute("products", products.getContent());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("hasNextPage", products.hasNext());
        model.addAttribute("user", user);
        return "productlistpaginated";
    }

    @GetMapping
    public String getAllProducts(Model model, @AuthenticationPrincipal MyUser user) {
        List<Product> products = productsService.getAllProducts();
        model.addAttribute("user", user);
        model.addAttribute("products", products);
        return "productlist";
    }

}
