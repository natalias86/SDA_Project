package pl.sda.sda_biuro_podrozy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sda.sda_biuro_podrozy.dto.OrderDto;
import pl.sda.sda_biuro_podrozy.order.OrderRepository;
import pl.sda.sda_biuro_podrozy.service.CartService;
import pl.sda.sda_biuro_podrozy.cart.ItemEntity;
import pl.sda.sda_biuro_podrozy.entities.OrderEntity;
import pl.sda.sda_biuro_podrozy.entities.UserEntity;
import pl.sda.sda_biuro_podrozy.order.OrderService;
import pl.sda.sda_biuro_podrozy.service.UserContextService;
import pl.sda.sda_biuro_podrozy.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class OrderController {
    CartService cartService;
    OrderService orderService;
    UserService userService;
    UserContextService userContextService;
    OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, CartService cartService, OrderService orderService, UserService userService, UserContextService userContextService) {

        this.cartService = cartService;
        this.orderService = orderService;
        this.userService = userService;
        this.userContextService = userContextService;
        this.orderRepository = orderRepository;

    }


    @GetMapping("/order/{orderId}")

    public String placeOrder(@PathVariable Integer orderId, OrderDto orderDto, Model model, RedirectAttributes redirectAttributes) {
        OrderDto order = new OrderDto();
        List<ItemEntity> items = cartService.getCartElements();
        if (!items.isEmpty()) {

            if (!userContextService.getUser().isPresent()) {
                return "redirect:/login";
            } else {
                order.setUserEntity(userContextService.getUser().get());
                order.setOrderDate(LocalDate.now());
                order.setTotalPrice(cartService.calculateTotalPrice());
            }
            model.addAttribute("order", order);
            model.addAttribute("user", userContextService.getUser().orElse(new UserEntity()));
            return "cart/order";
        } else {
            return "cart/cartEmpty";
        }
    }

     /*       @PostMapping("/order/{orderId}")(@PathVariable Integer orderId, OrderDto orderDto, Model model,
            OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order Invalid");
            //potwierdz zamowienie i odejmij dostepne miejsca  + cartService.clearCart();

        } else {
            return "cart"; //usun zamowienie o tym id
        }
    }*/

    }
