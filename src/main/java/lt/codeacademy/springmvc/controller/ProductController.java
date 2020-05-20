package lt.codeacademy.springmvc.controller;

import java.util.Optional;
import lt.codeacademy.springmvc.services.CustomerService;
import lt.codeacademy.springmvc.services.ProductsService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductsService productsService;
    private CustomerService customerService;

    public ProductController(
        ProductsService productsService,
        CustomerService customerService) {
        this.productsService = productsService;
        this.customerService = customerService;
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
    public String getProductsByPrice(
        @RequestParam Double price,
        @RequestParam int pageNumber,
        Model model
    ) {
        List<Product> products = productsService.getProductsByPrice(price, pageNumber);
        model.addAttribute("products", products);
        return "productlist";
    }

    @PostMapping("/product")
    public String submitProduct(@Valid Product product, BindingResult errors, Model model) {

        if (errors.hasErrors()) {
            return "productform";
        }

        Product newProduct = productsService.createOrUpdateProduct(product);
        model.addAttribute("product", newProduct);
        return "redirect:/products/" + newProduct.getId();
    }

    @GetMapping("/paginated")
    public String getProductsByPage(@RequestParam(defaultValue = "0") int pageNumber, Model model) {
        Page<Product> products = productsService.getAllProductsPaginated(pageNumber);
        model.addAttribute("products", products.getContent());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("hasNextPage", products.hasNext());
        return "productlistpaginated";
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "productlist";
    }
    @GetMapping("/checkout/buy")
    public String buyProduct(Model model) {
        model.addAttribute("customer", new Customer());
        return "productcheckout";
    }

    @PostMapping("/checkout/submit")
    public String checkoutSubmitProduct(@Valid Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "productcheckout";
        }
        customerService.saveOrUpdateCustomer(customer);
        return "productcheckoutinfo";
    }

}
