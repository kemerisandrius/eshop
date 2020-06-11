package lt.codeacademy.springmvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lt.codeacademy.springmvc.entities.DeliveryInfo;
import lt.codeacademy.springmvc.entities.Product;
import lt.codeacademy.springmvc.entities.User;
import lt.codeacademy.springmvc.services.CustomerService;
import lt.codeacademy.springmvc.services.ProductsService;
import lt.codeacademy.springmvc.services.validator.CustomerInfoValidator;

@Controller
@RequestMapping("/private/products")
@SessionAttributes("created")
public class ProductPrivateController {
    private ProductsService productsService;
    private CustomerService customerService;
    private CustomerInfoValidator customerInfoValidator;

    public ProductPrivateController(
            ProductsService productsService,
            CustomerService customerService,
            CustomerInfoValidator customerInfoValidator
    ) {
        this.productsService = productsService;
        this.customerService = customerService;
        this.customerInfoValidator = customerInfoValidator;
    }

    @GetMapping("/product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getUpdateProductForm(@PathVariable Long id, Model model, @AuthenticationPrincipal User user) {
        Product product = productsService.getProduct(id);
        model.addAttribute("user", user);
        model.addAttribute("product", product);
        return "productform";
    }

    @GetMapping("/product")
    @PreAuthorize("hasRole('ADMIN')")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "productform";
    }

    @GetMapping("/product/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(@PathVariable Long id, Model model) {
        productsService.deleteProduct(id);
        List<Product> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/product")
    @PreAuthorize("hasRole('ADMIN')")
    public String submitProduct(@Valid Product product, BindingResult errors, Model model, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "productform";
        }

        Product newProduct = productsService.createOrUpdateProduct(product);
        model.addAttribute("product", newProduct);

        redirectAttributes.addFlashAttribute("created", true);

        return "redirect:/public/products/" + newProduct.getId();
    }



    @PostMapping("/checkout/submit")
    public String checkoutSubmitProduct(@Valid DeliveryInfo deliveryInfo, BindingResult bindingResult, @AuthenticationPrincipal User user, Model model) {
        customerInfoValidator.validate(deliveryInfo, bindingResult);
        if (bindingResult.hasErrors()) {
            return "ordercheckout";
        }
        customerService.saveOrUpdateCustomer(deliveryInfo);
        model.addAttribute("user", user);
        return "ordercheckoutinfo";
    }
}
