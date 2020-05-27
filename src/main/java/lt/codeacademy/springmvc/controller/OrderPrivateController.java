package lt.codeacademy.springmvc.controller;

import lt.codeacademy.springmvc.entities.DeliveryInfo;
import lt.codeacademy.springmvc.entities.Order;
import lt.codeacademy.springmvc.entities.Product;
import lt.codeacademy.springmvc.entities.User;
import lt.codeacademy.springmvc.repositories.UserRepository;
import lt.codeacademy.springmvc.services.OrderService;
import lt.codeacademy.springmvc.services.ProductsService;
import lt.codeacademy.springmvc.services.SpringDataUserDetailsService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/private/orders")
public class OrderPrivateController {

    private OrderService orderService;
    private ProductsService productsService;
    private SpringDataUserDetailsService userService;

    public OrderPrivateController(
        OrderService orderService,
        ProductsService productsService,
        SpringDataUserDetailsService userService
    ) {
        this.orderService = orderService;
        this.productsService = productsService;
        this.userService = userService;
    }

    @PostMapping("/checkout/buy")
    public String checkout(
        @RequestParam("productId") Long productId,
        DeliveryInfo deliveryInfo,
        Model model,
        @AuthenticationPrincipal User user
    ) {
        Product product = productsService.getProduct(productId);

        Order order = new Order();
        order.setDeliveryInfo(deliveryInfo);
        order.setProduct(product);
        orderService.saveOrder(order);

        user.addDeliveryInfo(deliveryInfo);
        userService.saveOrUpdateUser(user);

        model.addAttribute("deliveryInfo", deliveryInfo);
        model.addAttribute("user", user);
        return "ordercheckoutinfo";
    }

    @GetMapping("/checkout")
    public String buyProduct(
        @RequestParam("productId") Long productId,
        Model model,
        @AuthenticationPrincipal User user
    ) {
        model.addAttribute("deliveryInfo", new DeliveryInfo());
        model.addAttribute("productId", productId);
        model.addAttribute("user", user);
        return "ordercheckout";
    }

    @GetMapping
    public String showOrderList(
        @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
        @AuthenticationPrincipal User user,
        Model model
    ) {
        Page<Order> userOrders = orderService.getUserOrders(user, pageNumber);
        model.addAttribute("userOrders", userOrders.getContent());
        model.addAttribute("user", user);
        return "orderhistory";
    }


}
