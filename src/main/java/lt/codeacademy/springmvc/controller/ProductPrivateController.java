package lt.codeacademy.springmvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.codeacademy.springmvc.services.CustomerService;
import lt.codeacademy.springmvc.services.ProductsService;

@Controller
@RequestMapping("/private/products")
public class ProductPrivateController {
    private ProductsService productsService;
    private CustomerService customerService;

    public ProductPrivateController(
            ProductsService productsService,
            CustomerService customerService) {
        this.productsService = productsService;
        this.customerService = customerService;
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

    @PostMapping("/product")
    public String submitProduct(@Valid Product product, BindingResult errors, Model model) {

        if (errors.hasErrors()) {
            return "productform";
        }

        Product newProduct = productsService.createOrUpdateProduct(product);
        model.addAttribute("product", newProduct);
        return "redirect:/products/" + newProduct.getId();
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
