package pl.sda.sda_biuro_podrozy.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.sda_biuro_podrozy.entities.OrderEntity;
import pl.sda.sda_biuro_podrozy.entities.UserEntity;
import pl.sda.sda_biuro_podrozy.order.OrderRepository;
import pl.sda.sda_biuro_podrozy.service.CartService;
import pl.sda.sda_biuro_podrozy.cart.ItemEntity;
import pl.sda.sda_biuro_podrozy.entities.PostEntity;

import pl.sda.sda_biuro_podrozy.repository.ItemRepository;
import pl.sda.sda_biuro_podrozy.repository.PostRepository;
import pl.sda.sda_biuro_podrozy.service.PostService;
import pl.sda.sda_biuro_podrozy.service.UserContextService;
import pl.sda.sda_biuro_podrozy.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class CartController {

    private PostRepository postRepository;
    private UserContextService userContextService;
    private PostService postService;
    private ItemRepository itemRepository;
    private CartService cartService;
    private UserService userService;
    private OrderRepository orderRepository;

    @Autowired
    public CartController(PostRepository postRepository, UserContextService userContextService, PostService postService, ItemRepository itemRepository, CartService cartService, UserService userService, OrderRepository orderRepository) {
        this.postRepository = postRepository;
        this.userContextService = userContextService;
        this.postService = postService;
        this.itemRepository = itemRepository;
        this.cartService = cartService;
        this.userService = userService;
        this.orderRepository=orderRepository;
    }

    @PostMapping("/post/{postId}")
    public String buy(@PathVariable Integer postId, @ModelAttribute("buyItem") @Valid ItemEntity itemEntity, BindingResult bindingResult, Model model) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId));

        cartService.addPostToCart(new ItemEntity(1, postEntity));
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String showCart(Model model) {

        model.addAttribute("totalPrice", cartService.calculateTotalPrice());
        model.addAttribute("cart", new CardForm(cartService.getCartElements()));

        return "cart/cartElements";
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardForm {
        private Collection<ItemEntity> items = new ArrayList<>();
    }

    @PostMapping("/cart")
    public String confirmOrder(@ModelAttribute @Valid CardForm cardForm, BindingResult bindingResult, Integer orderId, Model model) {
        OrderEntity order = new OrderEntity();
        if (bindingResult.hasErrors()) {

            return "cart/cartElements";
        }
        if (!userContextService.getUser().isPresent()) {
            return "redirect:/login";}
        else{

        List<ItemEntity> items =cartService.getCartElements();
            items.forEach(x -> {
            cartService.updateNumberOfTravelers(x.getNumberOfTravelers(), x.getItemId());
        });
        model.addAttribute("cart", cardForm);
        //OrderEntity order = new OrderEntity();
        for (ItemEntity item : cartService.getCartElements()) {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setPostEntity(item.getPostEntity());
            itemEntity.setNumberOfTravelers(item.getNumberOfTravelers());
//            itemEntity.setOrderEntity(order);
            order.addItem(itemEntity);
//            itemRepository.save(itemEntity);
            order.setUserEntity(userContextService.getUser().get());
            order.setOrderDate(LocalDate.now());
            order.setTotalPrice(cartService.calculateTotalPrice());
        }}

        OrderEntity savedOrder = orderRepository.save(order);
        return "redirect:/order/" + savedOrder.getOrderId();
    }

    @GetMapping("/cart/remove/{itemId}")
    public String removeItem(@PathVariable Integer itemId, Model model) {

        cartService.removeItemFromCart(itemId);

//TODO: update itemId
        return "redirect:/cart";
    }


}


