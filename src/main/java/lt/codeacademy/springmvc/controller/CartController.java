package lt.codeacademy.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lt.codeacademy.springmvc.entities.Product;
import lt.codeacademy.springmvc.services.ProductsService;

@Controller
@RequestMapping("/public/cart")
@SessionAttributes("cart")
public class CartController {

    private ProductsService productsService;

    public CartController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @ModelAttribute("cart")
    public List<Product> cart() {
        return new ArrayList<>();
    }

    @GetMapping
    public String openCart(Model model) {
        return "cart";
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, @ModelAttribute("cart") List<Product> cart, Model model) {
        cart.add(productsService.getProduct(productId));
        return "cart";
    }

    @GetMapping("/buy")
    public String buy(@ModelAttribute("cart") List<Product> cart, Model model, SessionStatus sessionStatus) {
        // ivykdom pirkima
        sessionStatus.setComplete();
        return "redirect:/public/cart";
    }
}