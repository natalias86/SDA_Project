package pl.sda.sda_biuro_podrozy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.sda_biuro_podrozy.service.CartService;
import pl.sda.sda_biuro_podrozy.cart.ItemEntity;
import pl.sda.sda_biuro_podrozy.entities.OrderEntity;
import pl.sda.sda_biuro_podrozy.entities.UserEntity;
import pl.sda.sda_biuro_podrozy.order.OrderService;
import pl.sda.sda_biuro_podrozy.service.UserContextService;
import pl.sda.sda_biuro_podrozy.service.UserService;

import java.util.List;

@Controller
public class OrderController {
    CartService cartService;
    OrderService orderService;
    UserService userService;
    UserContextService userContextService;

    @Autowired
    public OrderController(CartService cartService, OrderService orderService, UserService userService, UserContextService userContextService) {

        this.cartService = cartService;
        this.orderService = orderService;
        this.userService = userService;
        this.userContextService = userContextService;

    }


    @GetMapping("/order")

    public String placeOrder(Model model, RedirectAttributes redirectAttributes) {

        List<ItemEntity> items = cartService.getCartElements();
        if (!items.isEmpty()) {
            OrderEntity order = new OrderEntity();
            if (!userContextService.getUser().isPresent()) {
                return "redirect:/login";
            } else {
                order.setUserEntity(userContextService.getUser().get());
            }


            cartService.clearCart();
            model.addAttribute("order", order);
            model.addAttribute("user", userContextService.getUser().orElse(new UserEntity()));


            return "cart/order";

        } else {
            return "redirect:/cart";

        }
    }

}
