package lt.codeacademy.springmvc.controller;

import lt.codeacademy.springmvc.entities.Customer;
import lt.codeacademy.springmvc.entities.Order;
import lt.codeacademy.springmvc.entities.Product;
import lt.codeacademy.springmvc.entities.User;
import lt.codeacademy.springmvc.services.OrderService;
import lt.codeacademy.springmvc.services.ProductsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/private/orders")
public class OrderPrivateController {

    private OrderService orderService;
    private ProductsService productsService;

    public OrderPrivateController(
        OrderService orderService,
        ProductsService productsService
    ) {
        this.orderService = orderService;
        this.productsService = productsService;
    }

    @PostMapping("/checkout/buy")
    public String checkout(
        @RequestParam("productId") Long productId,
        Customer customer,
        Model model,
        @AuthenticationPrincipal User user
    ) {
        Product product = productsService.getProduct(productId);
        
        Order order = new Order();
        order.setCustomer(customer);
        order.setProduct(product);
        orderService.saveOrder(order);

        model.addAttribute("customer", customer);
        model.addAttribute("user", user);
        return "ordercheckoutinfo";
    }

    @GetMapping("/checkout")
    public String buyProduct(
        @RequestParam("productId") Long productId,
        Model model,
        @AuthenticationPrincipal User user
    ) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("productId", productId);
        model.addAttribute("user", user);
        return "ordercheckout";
    }


}
